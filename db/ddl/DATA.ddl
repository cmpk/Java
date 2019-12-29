CREATE TABLE data
(
    number_value NUMBER(1,0),
    string_value VARCHAR(32),
    date_value   DATE
)
;

CREATE USER scott IDENTIFIED BY tiger;
GRANT connect,resource TO scott;
GRANT select,update,delete,insert ON data TO scott;
