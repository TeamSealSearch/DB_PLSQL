CREATE OR REPLACE DIRECTORY PDF_FILES AS '/u01/userhome/oracle/test_PDF_Files';
/
--CREATE USER sealAdmin IDENTIFIED BY SSA1;
--GRANT ALL ON DIRECTORY PDF_FILES TO SSA1;
CREATE OR REPLACE FUNCTION file_to_blob(p_file_name VARCHAR2) RETURN BLOB AS
dest_loc BLOB := empty_blob();
src_loc BFILE := BFILENAME('PDF_FILES', p_file_name);
BEGIN
DBMS_LOB.OPEN(src_loc, DBMS_LOB.LOB_READONLY);

DBMS_LOB.CREATETEMPORARY(
lob_loc => dest_loc
, cache => true
, dur => dbms_lob.session
);

DBMS_LOB.OPEN(dest_loc, DBMS_LOB.LOB_READWRITE);

DBMS_LOB.LOADFROMFILE(
dest_lob => dest_loc
, src_lob => src_loc
, amount => DBMS_LOB.getLength(src_loc));

DBMS_LOB.CLOSE(dest_loc);
DBMS_LOB.CLOSE(src_loc);

RETURN dest_loc;
END file_to_blob;
/
DECLARE
v_blob BLOB;
BEGIN
v_blob := file_to_blob ('a79451fb185b9df2fc3564b32fda1fcb.pdf');

UPDATE EMPLOYER
SET e_joblistingpdf = v_blob
WHERE e_hashedid = 'Xp2s5v8y/A?D(G+K';

COMMIT;
END;
/