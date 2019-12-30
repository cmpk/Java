load data

characterset JA16SJIS

truncate into table DATA
fields terminated by ','
trailing nullcols
(
    NUMBER_VALUE,
    STRING_VALUE,
    DATE_VALUE          DATE(19) "YYYY/MM/DD HH24:MI:SS",
    NOTE                FILLER      -- スキップ（DBに投入しない）
)