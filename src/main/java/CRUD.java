import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dora on 2016/11/10.
 */
public class CRUD {

    private Connection conn;


    static Map<String, List<String>> routes;

    public CRUD(DB db) {
        String[] routeList={"G101","G102","G103","G104","G105","G106","G107","G108","G109","G11","G110","G111","G112","G113","G114","G115","G116","G117","G118","G119","G12","G120","G121","G122","G123","G124","G125","G126","G127","G128","G129","G13","G130","G131","G132","G133","G134","G135","G136","G137","G138","G139","G14","G140","G141","G142","G143","G144","G145","G146","G147","G148","G149","G15","G150","G151","G152","G153","G154","G155","G156","G157","G158","G159","G16","G160","G161","G162","G163","G164","G165","G166","G167","G168","G17","G171","G172","G173","G174","G175","G176","G177","G178","G179","G18","G180","G181","G182","G183","G184","G185","G186","G187","G188","G189","G19","G190","G191","G192","G193","G194","G195","G196","G197","G198","G199","G20","G21","G22","G25","G26","G27","G28","G29","G30","G31","G32","G33","G34","G35","G36","G37","G38","G39","G40","G41","G42","G43","G44","G45","G46","G47","G48","G49","G50","G51","G52","G53","G54","G55","G56","G57","G58","G59","G6","G60","G61","G62","G63","G64","G65","G66","G67","G68","G69","G7","G70","G71","G72","G73","G74","G75","G76","G77","G78","G79","G80","G81","G82","G83","G84","G85","G86","G88","G89","G90","G93","G94","G95","G96","G97","G98","﻿G5"};
        String[] stopsList = {"北京南-沧州西-德州东-济南西-泰安-枣庄-徐州东-南京南-镇江南-苏州北-上海虹桥","上海虹桥-苏州北-常州北-南京南-宿州东-枣庄-济南西-天津南-北京南","北京南-沧州西-济南西-滕州东-徐州东-蚌埠南-南京南-无锡东-上海虹桥","上海虹桥-无锡东-南京南-滁州-徐州东-济南西-沧州西-北京南","北京南-廊坊-沧州西-济南西-徐州东-宿州东-南京南-常州北-苏州北-上海虹桥","上海虹桥-苏州北-镇江南-南京南-徐州东-泰安-济南西-天津南-北京南","北京南-德州东-济南西-枣庄-徐州东-南京南-丹阳北-苏州北-上海虹桥","上海虹桥-昆山南-无锡东-丹阳北-南京南-蚌埠南-徐州东-枣庄-济南西-德州东-北京南","北京南-德州东-济南西-曲阜东-徐州东-滁州-南京南-常州北-苏州北-上海虹桥","北京南-济南西-曲阜东-南京南-苏州北-上海虹桥","上海虹桥-苏州北-常州北-镇江南-南京南-滁州-宿州东-徐州东-曲阜东-济南西-北京南","北京南-沧州西-济南西-曲阜东-宿州东-定远-南京南-常州北-昆山南-上海虹桥","上海虹桥-苏州北-南京南-定远-徐州东-济南西-沧州西-天津南-廊坊-北京南","北京南-天津南-德州东-济南西-徐州东-南京南-苏州北-上海虹桥","上海虹桥-昆山南-苏州北-无锡东-镇江南-南京南-蚌埠南-徐州东-济南西-沧州西-北京南","北京南-天津南-德州东-济南西-泰安-徐州东-南京南-无锡东-昆山南-上海虹桥","上海虹桥-无锡东-常州北-南京南-蚌埠南-徐州东-曲阜东-济南西-德州东-北京南","北京南-德州东-济南西-泰安-枣庄-滁州-南京南-丹阳北-苏州北-昆山南-上海虹桥","上海虹桥-苏州北-无锡东-丹阳北-南京南-滁州-徐州东-曲阜东-济南西-北京南","北京南-沧州西-济南西-曲阜东-枣庄-徐州东-南京南-镇江南-无锡东-上海虹桥","上海虹桥-常州北-南京南-徐州东-泰安-济南西-北京南","上海虹桥-昆山南-苏州北-常州北-南京南-滁州-徐州东-滕州东-济南西-天津南-北京南","北京南-廊坊-天津南-德州东-济南西-枣庄-徐州东-南京南-常州北-无锡东-苏州北-上海虹桥","上海虹桥-苏州北-常州北-南京南-徐州东-曲阜东-泰安-济南西-德州东-北京南","北京南-天津南-德州东-济南西-枣庄-滁州-南京南-常州北-苏州北-昆山南-上海虹桥","上海虹桥-无锡东-南京南-蚌埠南-徐州东-枣庄-济南西-德州东-天津南-廊坊-北京南","北京南-天津南-德州东-济南西-滕州东-蚌埠南-南京南-丹阳北-常州北-昆山南-上海虹桥","上海虹桥-苏州北-南京南-定远-徐州东-枣庄-济南西-德州东-沧州西-天津南-北京南","北京南-沧州西-德州东-济南西-泰安-徐州东-宿州东-南京南-无锡东-上海虹桥","上海虹桥-苏州北-镇江南-南京南-滁州-宿州东-徐州东-曲阜东-济南西-德州东-天津南-北京南","北京南-天津南-德州东-济南西-徐州东-宿州东-南京南-镇江南-无锡东-上海虹桥","北京南-济南西-南京南-上海虹桥","上海虹桥-苏州北-无锡东-常州北-南京南-滁州-徐州东-枣庄-滕州东-济南西-德州东-北京南","北京南-沧州西-济南西-泰安-枣庄-徐州东-滁州-南京南-苏州北-昆山南-上海虹桥","上海虹桥-苏州北-常州北-南京南-宿州东-徐州东-曲阜东-济南西-天津南-北京南","北京南-天津南-德州东-济南西-曲阜东-徐州东-滁州-南京南-镇江南-常州北-无锡东-上海虹桥","上海虹桥-昆山南-无锡东-南京南-蚌埠南-徐州东-济南西-天津南-北京南","北京南-廊坊-沧州西-济南西-徐州东-宿州东-滁州-南京南-常州北-无锡东-上海虹桥","上海虹桥-昆山南-常州北-镇江南-南京南-徐州东-泰安-济南西-德州东-廊坊-北京南","北京南-沧州西-济南西-泰安-滕州东-徐州东-滁州-南京南-丹阳北-无锡东-上海虹桥","上海虹桥-苏州北-常州北-南京南-滁州-曲阜东-泰安-济南西-天津南-北京南","北京南-天津南-德州东-济南西-枣庄-徐州东-蚌埠南-滁州-南京南-无锡东-上海虹桥","上海虹桥-南京南-济南西-北京南","上海虹桥-苏州北-无锡东-镇江南-南京南-枣庄-曲阜东-济南西-德州东-天津南-北京南","北京南-德州东-济南西-泰安-徐州东-宿州东-蚌埠南-滁州-南京南-常州北-无锡东-苏州北-上海虹桥","上海虹桥-昆山南-无锡东-南京南-蚌埠南-徐州东-枣庄-泰安-济南西-沧州西-北京南","北京南-沧州西-济南西-曲阜东-徐州东-滁州-南京南-镇江南-无锡东-苏州北-上海虹桥","上海虹桥-昆山南-常州北-南京南-蚌埠南-徐州东-枣庄-济南西-德州东-廊坊-北京南","北京南-沧州西-济南西-泰安-枣庄-徐州东-南京南-镇江南-常州北-无锡东-苏州北-上海虹桥","上海虹桥-昆山南-无锡东-镇江南-南京南-徐州东-曲阜东-泰安-济南西-天津南-北京南","北京南-天津南-沧州西-德州东-济南西-曲阜东-枣庄-滁州-南京南-镇江南-丹阳北-苏州北-上海虹桥","上海虹桥-苏州北-常州北-南京南-宿州东-徐州东-曲阜东-济南西-沧州西-北京南","北京南-沧州西-德州东-济南西-滕州东-徐州东-宿州东-南京南-镇江南-无锡东-昆山南-上海虹桥","北京南-济南西-南京南-上海虹桥","上海虹桥-苏州北-丹阳北-南京南-徐州东-枣庄-曲阜东-济南西-德州东-天津南-北京南","北京南-天津南-德州东-济南西-曲阜东-枣庄-徐州东-宿州东-南京南-常州北-苏州北-上海虹桥","上海虹桥-苏州北-南京南-徐州东-曲阜东-济南西-德州东-天津南-北京南","北京南-沧州西-济南西-泰安-徐州东-蚌埠南-南京南-苏州北-上海虹桥","上海虹桥-苏州北-镇江南-南京南-滁州-滕州东-济南西-北京南","北京南-天津南-德州东-济南西-滕州东-徐州东-南京南-无锡东-苏州北-昆山南-上海虹桥","上海虹桥-苏州北-无锡东-南京南-滁州-枣庄-济南西-廊坊-北京南","北京南-德州东-济南西-曲阜东-徐州东-南京南-常州北-无锡东-上海虹桥","上海虹桥-昆山南-常州北-丹阳北-南京南-蚌埠南-曲阜东-泰安-济南西-沧州西-北京南","北京南-沧州西-济南西-枣庄-宿州东-南京南-镇江南-上海虹桥","上海虹桥-南京南-济南西-北京南","上海虹桥-昆山南-苏州北-常州北-南京南-蚌埠南-徐州东-滕州东-济南西-北京南","北京南-廊坊-天津南-沧州西-济南西-泰安-滕州东-徐州东-蚌埠南-淮南东-水家湖-合肥南-巢湖东-无为-铜陵北-铜陵-池州-安庆","安庆-池州-铜陵-铜陵北-无为-合肥南-淮南东-蚌埠南-徐州东-滕州东-济南西-德州东-天津南-北京南","北京南-天津南-德州东-济南西-泰安-曲阜东-滕州东-徐州东-滁州-南京南-溧水-宜兴-长兴-杭州东-诸暨-义乌-金华南-武义北-永康南-缙云西-丽水-青田-温州南-瑞安-鳌江-苍南","苍南-鳌江-瑞安-温州南-青田-丽水-缙云西-永康南-武义北-金华南-义乌-诸暨-杭州东-湖州-宜兴-溧阳-溧水-南京南-徐州东-滕州东-泰安-济南西-德州东-天津南-廊坊-北京南","北京南-德州东-济南西-曲阜东-徐州东-蚌埠南-滁州-南京南-溧水-溧阳-宜兴-湖州-杭州东","杭州东-德清-湖州-宜兴-南京南-宿州东-徐州东-曲阜东-泰安-济南西-德州东-天津南-北京南","北京南-天津南-德州东-济南西-徐州东-滁州-南京南-溧水-溧阳-湖州-杭州东-绍兴北-余姚北-宁波-临海-台州-温岭-温州南","温州南-温岭-台州-临海-宁波-余姚北-绍兴北-杭州东-德清-湖州-宜兴-溧阳-溧水-南京南-蚌埠南-徐州东-泰安-济南西-德州东-天津南-北京南","北京南-济南西-南京南-上海虹桥","天津西-沧州西-德州东-济南西-济南-章丘-淄博-青州市-潍坊-青岛","青岛-高密-潍坊-淄博-济南-济南西-沧州西-天津西","天津西-沧州西-济南西-济南-淄博-青州市-潍坊-胶州北-青岛","青岛-青岛北-潍坊-淄博-济南-济南西-沧州西-天津西","北京南-天津南-德州东-济南西-济南-淄博-潍坊-胶州北-青岛","潍坊-青州市-淄博-济南-济南西-德州东-天津南-廊坊-北京南","北京南-天津南-德州东-济南西-济南-淄博-潍坊-青岛","青岛-高密-潍坊-淄博-章丘-济南-济南西-沧州西-北京南","北京南-德州东-济南西-济南-淄博-潍坊-青岛","上海虹桥-南京南-济南西-北京南","青岛-高密-潍坊-青州市-淄博-济南-济南西-德州东-北京南","北京南-天津南-德州东-济南西-济南-淄博-青州市-潍坊-青岛","青岛-青岛北-潍坊-淄博-济南-济南西-沧州西-北京南","北京南-廊坊-德州东-济南西-济南-淄博-潍坊-高密-胶州北-青岛","青岛-胶州北-潍坊-昌乐-淄博-济南-济南西-沧州西-北京南","北京南-沧州西-济南西-济南-章丘-淄博-昌乐-潍坊-青岛","青岛-青岛北-潍坊-青州市-淄博-济南-济南西-天津南-廊坊-北京南","北京南-天津南-德州东-济南西-济南-淄博-潍坊-青岛北-青岛","青岛-胶州北-潍坊-淄博-济南-济南西-德州东-北京南","北京南-廊坊-沧州西-济南西-济南-淄博-潍坊-高密-青岛","北京南-天津南-济南西-徐州东-南京南-上海虹桥","青岛-潍坊-淄博-济南-济南西-德州东-沧州西-天津南-北京南","北京南-德州东-济南西-济南-淄博-潍坊-高密-青岛","青岛-潍坊-淄博-济南-济南西-沧州西-北京南","北京南-沧州西-德州东-济南西-济南-淄博-青州市-潍坊-青岛","青岛-潍坊-青州市-淄博-济南-济南西-天津南-廊坊-北京南","北京南-廊坊-德州东-济南西-济南-淄博-潍坊-青岛","青岛-高密-潍坊-淄博-济南-济南西-天津南-北京南","北京南-天津南-德州东-济南西-济南-淄博-青州市-潍坊-青岛","青岛-青岛北-潍坊-青州市-淄博-济南-济南西-沧州西-北京南","北京南-廊坊-德州东-济南西-济南-淄博-青州市-潍坊-高密-青岛","上海虹桥-无锡东-南京南-滕州东-济南西-沧州西-北京南","北京南-天津南-济南西-泰安-滕州东-徐州东-南京南-镇江南-苏州北-上海虹桥","上海虹桥-无锡东-南京南-徐州东-曲阜东-泰安-济南西-北京南","北京西-石家庄-郑州东-西安北","西安北-郑州东-石家庄-北京西","北京南-德州东-济南西-合肥南-泾县-黄山北-上饶-武夷山东-福州","福州-武夷山东-上饶-黄山北-泾县-合肥南-济南西-天津南-北京南","北京南-天津南-济南西-合肥南","合肥南-济南西-天津南-北京南","北京南-济南西-南京南-湖州-杭州东","杭州东-湖州-南京南-济南西-北京南","北京南-沧州西-济南西-滕州东-徐州东-宿州东-南京南-宜兴-湖州-杭州东-义乌-金华-衢州-玉山南-上饶-鹰潭北-进贤南-南昌西","杭州东-湖州-宜兴-溧阳-江宁-南京南-宿州东-徐州东-滕州东-济南西-德州东-北京南","北京南-沧州西-济南西-曲阜东-徐州东-南京南-溧水-长兴-湖州-杭州东","杭州东-德清-湖州-句容西-南京南-宿州东-徐州东-枣庄-济南西-德州东-北京南","北京南-天津南-沧州西-德州东-济南西-徐州东-宿州东-蚌埠南-南京南-溧阳-宜兴-湖州-杭州东","南昌西-进贤南-鹰潭北-上饶-玉山南-金华-义乌-诸暨-杭州东-湖州-宜兴-溧水-南京南-蚌埠南-徐州东-枣庄-济南西-德州东-天津南-北京南","北京南-廊坊-沧州西-济南西-枣庄-徐州东-南京南-溧阳-长兴-湖州-杭州东","杭州东-湖州-长兴-宜兴-南京南-徐州东-滕州东-泰安-济南西-沧州西-北京南","北京南-德州东-济南西-曲阜东-蚌埠南-南京南-无锡东-上海虹桥-嘉兴南-杭州东","杭州东-桐乡-嘉兴南-上海虹桥-无锡东-镇江南-南京南-徐州东-泰安-济南西-天津南-北京南","北京南-德州东-济南西-曲阜东-徐州东-南京南-镇江南-无锡东-上海虹桥-嘉兴南-桐乡-杭州东","杭州东-嘉兴南-嘉善南-上海虹桥-昆山南-苏州北-镇江南-南京南-徐州东-济南西-天津南-北京南","北京南-天津南-沧州西-德州东-济南西-枣庄-徐州东-南京南-溧水-宜兴-湖州-德清-杭州东-诸暨-义乌-金华-衢州-江山","江山-衢州-金华-义乌-诸暨-杭州东-湖州-长兴-宜兴-南京南-徐州东-滕州东-济南西-德州东-廊坊-北京南","大连北-沈阳北-长春西-哈尔滨西","哈尔滨西-长春西-沈阳北-大连北","大连北-沈阳北-长春西-哈尔滨西","哈尔滨西-长春西-沈阳北-大连北","天津西-沧州西-德州东-济南西-曲阜东-滕州东-徐州东-宿州东-南京南-宜兴-杭州东-绍兴北-余姚北-宁波","宁波-绍兴北-杭州东-湖州-长兴-宜兴-瓦屋山-南京南-蚌埠南-徐州东-济南西-德州东-天津西","天津西-沧州西-济南西-曲阜东-徐州东-南京南-句容西-溧阳-宜兴-德清-杭州东","杭州东-德清-湖州-宜兴-南京南-蚌埠南-徐州东-泰安-济南西-沧州西-天津西","北京南-德州东-济南西-曲阜东-徐州东-南京南-江宁-湖州-杭州东-宁波-台州-温岭-温州南-苍南-福鼎-霞浦-宁德-福州","福州-宁德-霞浦-福鼎-苍南-温州南-温岭-台州-宁波-杭州东-湖州-长兴-溧阳-溧水-南京南-徐州东-枣庄-曲阜东-济南西-沧州西-天津南-北京南","北京南-天津南-德州东-济南西-枣庄-徐州东-滁州-南京南-宜兴-德清-杭州东-绍兴北-上虞北-宁波","宁波-上虞北-绍兴北-杭州东-长兴-溧水-南京南-徐州东-枣庄-曲阜东-济南西-德州东-北京南","北京南-天津南-德州东-济南西-枣庄-徐州东-蚌埠南-南京南-宜兴-湖州-杭州东-绍兴北-余姚北-宁波","上海虹桥-南京南-济南西-北京南","宁波-余姚北-绍兴北-杭州东-德清-江宁-南京南-蚌埠南-徐州东-枣庄-泰安-济南西-廊坊-北京南","济南西-泰安-曲阜东-枣庄-徐州东-宿州东-南京南-溧水-宜兴-湖州-杭州东-金华-衢州-上饶-鹰潭北-南昌西","南昌西-进贤南-鹰潭北-上饶-衢州-金华-义乌-杭州东-德清-湖州-长兴-宜兴-溧阳-南京南-蚌埠南-宿州东-徐州东-枣庄-曲阜东-泰安-济南西","济南-曲阜东-滕州东-枣庄-徐州东-宿州东-蚌埠南-滁州-南京南-宜兴-德清-杭州东-绍兴北-宁波-三门县-台州-温岭-温州南","温州南-温岭-台州-临海-宁波-绍兴北-杭州东-德清-长兴-宜兴-江宁-南京南-滁州-蚌埠南-宿州东-徐州东-枣庄-曲阜东-泰安-济南西","北京西-保定东-定州东-石家庄-邢台东-安阳东-郑州东-许昌东-信阳东-武汉-咸宁北-岳阳东-长沙南-衡山西-郴州西-清远-广州南","广州南-长沙南-武汉-郑州东-石家庄-北京西","北京西-保定东-正定机场-石家庄-邢台东-邯郸东-新乡东-郑州东-许昌东-漯河西-信阳东-武汉-岳阳东-长沙南-衡山西-郴州西-英德西-广州南","广州南-韶关-耒阳西-株洲西-长沙南-岳阳东-武汉-信阳东-驻马店西-漯河西-郑州东-鹤壁东-安阳东-高邑西-石家庄-北京西","北京西-定州东-石家庄-邯郸东-安阳东-郑州东-驻马店西-武汉-长沙南-株洲西-衡阳东-韶关-广州南","北京南-济南西-南京南-上海虹桥","广州南-韶关-郴州西-衡山西-长沙南-岳阳东-武汉-信阳东-明港东-郑州东-安阳东-邯郸东-石家庄-保定东-涿州东-北京西","北京西-高碑店东-保定东-石家庄-邢台东-邯郸东-鹤壁东-郑州东-驻马店西-孝感北-武汉-长沙南-衡阳东-耒阳西-韶关-广州南-虎门-深圳北","深圳北-广州南-郴州西-衡阳东-长沙南-岳阳东-武汉-驻马店西-漯河西-郑州东-安阳东-邢台东-石家庄-保定东-北京西","郑州东-许昌东-漯河西-信阳东-武汉-岳阳东-长沙南-郴州西-韶关-广州南-虎门-深圳北","深圳北-庆盛-广州南-英德西-郴州西-耒阳西-长沙南-岳阳东-武汉-信阳东-驻马店西-漯河西-许昌东-郑州东","郑州东-许昌东-漯河西-驻马店西-信阳东-武汉-长沙南-株洲西-衡阳东-韶关-广州南-虎门-深圳北","深圳北-虎门-广州南-清远-郴州西-衡阳东-长沙南-武汉-信阳东-驻马店西-漯河西-郑州东","武汉-长沙南-广州南-深圳北","深圳北-广州南-长沙南-武汉","北京西-石家庄-郑州东-武汉-长沙南-广州南-深圳北","深圳北-虎门-广州南-清远-韶关-衡山西-长沙南-岳阳东-咸宁北-武汉-信阳东-许昌东-郑州东-新乡东-安阳东-邯郸东-石家庄-保定东-北京西","北京西-石家庄-郑州东-武汉-长沙南-怀化南-贵阳北","贵阳北-怀化南-长沙南-武汉-郑州东-石家庄-北京西","北京西-石家庄-郑州东-武汉-长沙南","长沙南-武汉-郑州东-石家庄-北京西","上海虹桥-杭州东-长沙南-广州南","广州南-长沙南-杭州东-上海虹桥","西安北-郑州东-石家庄-北京西","北京西-石家庄-郑州东-郑州","郑州-郑州东-石家庄-北京西","郑州东-许昌东-驻马店西-武汉-长沙南-衡阳东-广州南","广州南-郴州西-衡阳东-长沙南-汨罗东-武汉-信阳东-许昌东-郑州东","西安北-郑州-武汉-长沙南-广州南","广州南-长沙南-武汉-郑州-西安北","广州南-长沙南-武汉-郑州-西安北","西安北-郑州-武汉-长沙南-广州南","北京南-济南西-南京南-上海虹桥"};

        routes = new HashMap<String, List<String>>();

        for(int i=0;i<routeList.length;i++) {
            routes.put(routeList[i], Arrays.asList(stopsList[i].split("-")));
        }

        this.conn = db.getConnection();
    }


    public int buyTicket(int scheduleId, String route, String from, String to, String type) throws SQLException {


        List<String> stopList = routes.get(route);
        stopList = stopList.subList(stopList.indexOf(from), stopList.indexOf(to));

        String stopNames = parseStopNames(stopList);

        PreparedStatement ps = null;

        try {
            conn.setAutoCommit(false);
            String sql = "SELECT id,carriageNum,seatNum FROM seat WHERE type=? AND id NOT in( " +
                    "SELECT seatId FROM seats_sold WHERE stopName in (" + stopNames + ")) ORDER BY RAND() LIMIT 1;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, type);

            System.out.println("正在为您查询..."+route+": "+from+" 发往 "+to+" "+type);
            long start = System.currentTimeMillis();
            ResultSet rs = ps.executeQuery();

            long end = System.currentTimeMillis();
            System.out.println("查询完成！查询时间：" + (end - start) / 1000.0 + "s");

            int seatId = 0;
            while (rs.next()) {
                seatId = rs.getInt(1);
                int carriageNum = rs.getInt(2);
                int seatNum = rs.getInt(3);
                System.out.println("正在为您下单..." + carriageNum + "车" + seatNum + "座");
            }

            if (seatId!=0)
                buyTicket(scheduleId, stopList, seatId);
            else
                System.out.println("fail");


            conn.setAutoCommit(true);
            end = System.currentTimeMillis();
            System.out.println("购票完成！总时间：" + (end - start) / 1000.0 + "s");
            System.out.println("===============================");
        } catch (BatchUpdateException b) {

        } finally {
            if (ps != null) {
                ps.close();
            }
            this.conn.setAutoCommit(true);
        }

        return 0;
    }

    private void buyTicket(int scheduleId, List<String> stopNames, int seatId) throws SQLException, BatchUpdateException {

        PreparedStatement ps = conn.prepareStatement("insert into seats_sold(scheduleId,stopName,seatId) values(?,?,?);");

        for (String stopName : stopNames) {
            ps.setInt(1, scheduleId);
            ps.setString(2, stopName);
            ps.setInt(3, seatId);
            ps.addBatch();
        }

        ps.executeBatch();
        conn.commit();
    }



    public int queryTickets(String from, String to, String date) throws SQLException {
        String sql = "SELECT routeId,scheduleId FROM `schedule` " +
                "WHERE scheduleId in(SELECT scheduleId FROM timetable WHERE stopName=?AND leaveAt like '" + date + "%' )" +
                "AND routeId in (SELECT routeId FROM route where route.stopNames like '%" + from + "%" + to + "%');";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, from);


        long start = System.currentTimeMillis();
        ResultSet resultSet = ps.executeQuery();
        long end = System.currentTimeMillis();
        int i =0;
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1)+":schedule="+resultSet.getInt(2));
            queryTickets(resultSet.getInt(2),resultSet.getString(1),from,to);
            i++;
        }
        System.out.println("共"+i+"条结果");
        System.out.println("历经时间：" + (end - start) / 1000.0 + "s");
        System.out.println("===============================");

        return 0;
    }


  
    private int queryTickets(int scheduleId, String routeId, String from, String to) throws SQLException {

        String stopNames = parseStopNames(routeId,from,to);
        
        String sql = "select seattype.type as type, seattype.seats-IFNULL(sold,0) as remain FROM" +
                "(select type,ifnull(MAX(amount),0) as sold " +
                "from sold_amount WHERE scheduleId = ? AND stopName in ("+stopNames+") " +
                "GROUP BY type) t RIGHT JOIN seattype ON seattype.type = t.type;";


        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, scheduleId);


//        long start = System.currentTimeMillis();
        ResultSet resultSet = ps.executeQuery();
//        long end = System.currentTimeMillis();
//        System.out.println("列车线路ID："+scheduleId);
        while (resultSet.next()) {
            System.out.print(resultSet.getString("type")+": "+resultSet.getInt("remain")+"张"+" ");
        }
        System.out.println();
//        System.out.println("历经时间：" + (end - start) / 1000.0 + "s");

        return 0;
    }



    private void ignore() {
        int count =0;
        String sql = "SET FOREIGN_KEY_CHECKS=0;";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String parseStopNames(String routeId, String from, String to) {
        String stopNames = routes.get(routeId).toString();
        stopNames = stopNames.substring(stopNames.indexOf(from), stopNames.indexOf(to));
        stopNames = "'" + stopNames.replaceAll(", ", "','") + "'";
        return stopNames;
    }

    private String parseStopNames(List<String> stopList) {
        String stopNames = stopList.toString();
        stopNames = stopNames.substring(1,stopNames.length()-1);
        stopNames = "'" + stopNames.replaceAll(", ", "','") + "'";
        return stopNames;
    }
}
