--划小数据比对（注意账期）
--1、查看本地tpss_hx_data中的划小数据数目是否与远程接口提供的数据数目相同
select substr(a.area_id,6,2), count(*),sum(a.final_settle_fee)
                             from odsoutput.i_out_bi_contract_unit_assess@ods_dblink a
                            where a.acct_month = 201707
                              --  and  substr(a.area_id,6,2) = 53
                                group by substr(a.area_id,6,2);
                              
--2、查看本地划小数据表tpss_hx_data中已经存在哪些本地网的数据 
select distinct t.latn_id   from tpss_hx_data t where t.acct_month = 201707;

--3、查看划小数据表tpss_hx_data中是否存在一些异常的渠道
select distinct t.settle_channel_id,reason  from tpss_hx_data t where t.latn_id = 53  and t.acct_month = 201707 and t.reason<>'1';
