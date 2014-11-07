--select table_name from user_tables
CREATE OR REPLACE PROCEDURE "CREATEPOSTABLE" (
  simId char
)
authid current_user
is
  v_counter number := 0;
  sqlStr varchar2(1000);
  tableName varchar2(30);
begin
  tableName := 'Tracks_For_' || simId;
  select count(*) into v_counter from user_tables where table_name = upper(tableName);
  if v_counter = 0 then
    sqlStr := 'create table ' || tableName || '(';
    sqlStr := sqlStr || 'DateTime date not null,';
    sqlStr := sqlStr || 'Longitude number(9,6) not null,';
    sqlStr := sqlStr || 'Latitude number(9,6) not null,';
    sqlStr := sqlStr || 'GpsSpeed number(4) ,';
    sqlStr := sqlStr || 'RecorerdSpeed number(4),';
    sqlStr := sqlStr || 'Angle number(3) ,';
    sqlStr := sqlStr || 'AlarmFlag number(10) ,';
    sqlStr := sqlStr || 'Height number(5) ,';
    sqlStr := sqlStr || 'Status number(10) ,';
    sqlStr := sqlStr || 'Oil number(4) ,';
    sqlStr := sqlStr || 'Voltage number(4) ,';
    sqlStr := sqlStr || 'Miles number(10)';
    sqlStr := sqlStr || ')';
    EXECUTE IMMEDIATE sqlStr;
 end if;
end;