import java.sql.SQLException;

/**
 * Created by Dora on 2016/11/10.
 */
public class test {

    public static void main(String[] args) {
        DB db = DB.getInstance();
        db.openConnection();
        try {
            CRUD crud = new CRUD(db);

//            for (int i =0 ; i< 14;i++) {
//                crud.buyTicket(1, "G101", "北京南", "上海虹桥", "二等座");
//                crud.buyTicket(1, "G101", "徐州东", "上海虹桥", "一等座");
//                crud.buyTicket(41, "G101", "南京南", "上海虹桥", "一等座");
//                crud.buyTicket(121, "G101", "徐州东", "苏州北", "一等座");
//            }

            crud.queryTickets("徐州东","南京南","2016-11-11");

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        db.closeConnection();
    }
}
