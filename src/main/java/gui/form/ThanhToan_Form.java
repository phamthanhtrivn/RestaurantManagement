/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.form;

import dao.BanDAO;
import dao.ChiTietHoaDonDAO;
import dao.DonDatBanDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.KhuyenMaiDAO;
import dao.LoaiBanDAO;
import dao.LoaiKhachHangDAO;
import dao.LoaiMonAnDAO;
import dao.MonAnDAO;
import dao.NhanVienDAO;
import dao.impl.BanDAOImpl;
import dao.impl.ChiTietHoaDonDAOImpl;
import dao.impl.DonDatBanDAOImpl;
import dao.impl.HoaDonDAOImpl;
import dao.impl.KhachHangDAOImpl;
import dao.impl.KhuyenMaiDAOImpl;
import dao.impl.LoaiBanDAOImpl;
import dao.impl.LoaiKhachHangDAOImpl;
import dao.impl.LoaiMonAnDAOImpl;
import dao.impl.MonAnDAOImpl;
import dao.impl.NhanVienDAOImpl;
import gui.swing.table.TableCustom;
import java.awt.Color;
import java.awt.Font;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;

import gui.main.ThuNgan_DashBoard;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Ban;
import model.ChiTietHoaDon;
import model.DonDatBan;
import model.HoaDon;
import model.KhachHang;
import model.KhuyenMai;
import model.LoaiBan;
import model.LoaiKhachHang;
import model.LoaiMonAn;
import model.MonAn;
import model.NhanVien;
import net.sf.jasperreports.engine.JRException;
import print.ReportManager;
import print.model.FieldReportDonTamTinh;
import print.model.FieldReportHoaDon;
import print.model.ParameterReportDonTamTinh;
import print.model.ParameterReportHoaDon;
/**
 *
 * @author Thanh Tuan
 */
public class ThanhToan_Form extends javax.swing.JFrame {
    private MonAnDAO monAn_dao = new MonAnDAOImpl(MonAn.class);
    private KhuyenMaiDAO km_dao = new KhuyenMaiDAOImpl(KhuyenMai.class);
    private LoaiKhachHangDAO loaiKh_dao = new LoaiKhachHangDAOImpl(LoaiKhachHang.class);
    private KhachHangDAO kh_dao = new KhachHangDAOImpl(KhachHang.class);
    private HoaDonDAO hd_dao = new  HoaDonDAOImpl(HoaDon.class);
    private ChiTietHoaDonDAO cthd_dao =  new ChiTietHoaDonDAOImpl(ChiTietHoaDon.class);
    private BanDAO ban_dao = new BanDAOImpl(Ban.class);
    private LoaiMonAnDAO loaiMa_dao = new LoaiMonAnDAOImpl(LoaiMonAn.class);
    private LoaiBanDAO loaiBan_dao = new LoaiBanDAOImpl(LoaiBan.class);
    private NhanVienDAO nv_dao = new NhanVienDAOImpl(NhanVien.class);
    private String maHD = "";
    private HoaDon hd = null;
    private Ban ban = null;
    private KhachHang kh = null;
    private LoaiKhachHang loaiKh = null;
    private KhuyenMai km = null;
    private DonDatBan ddb = null;
    private DonDatBanDAO ddb_dao = new DonDatBanDAOImpl(DonDatBan.class);
    private List<ChiTietHoaDon>  list = new ArrayList<ChiTietHoaDon>();
    private NhanVien nv = null;
    private ThuNgan_DashBoard dashBoard;
    /**
     * Creates new form ThanhToan_Form
     */
    public ThanhToan_Form(String maHD, NhanVien nv, ThuNgan_DashBoard dashBoard) {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.maHD = maHD;
        this.nv = nv;
        this.dashBoard = dashBoard;
        customTable();
        loadHoaDon();
    }
    
    //set method
    public void setHoaDon(){
        this.hd = hd_dao.findById(maHD);
        this.list = cthd_dao.getOrderDetails(maHD);
        if(hd.getDonDatBan() != null){
           this.ddb = ddb_dao.getDDBForHD(hd.getDonDatBan().getMaDDB());
        }
        
    }

    public void setBan(){
        this.ban = ban_dao.getBan(hd.getBan().getMaBan());
    }
    
    public void setKhuyenMai(KhuyenMai km){
        this.km = km;
    }
    
    public void setKhachHang(KhachHang kh){
        this.kh = kh;
        try{
            this.loaiKh = loaiKh_dao.TimLoaiKhachHangTim(kh.getLoaiKhachHang().getMaLoaiKH());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng");
        }
    }
    
    
    private void customTable() {
        TableCustom.apply(jScroll, TableCustom.TableType.MULTI_LINE);
        orderTable.getTableHeader().setFont(new Font("Sanserif", Font.BOLD, 12));
        orderTable.getTableHeader().setBackground(new Color(50, 50, 50));
        orderTable.repaint();
    }
    
    //format 
    
    public String currencyFormat(double price){
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.format(price);
    }
    
    public double currencyFormatToDouble(String currency){
        String str = currency.replaceAll("[^\\d]", "");
        return Double.parseDouble(str);
    }
    
    public String formatLocalDateTime(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return dateTime.format(formatter);
    }
    
    public String dateFormat(LocalDate dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dateTime.format(formatter);
    }
    
    public String timeFormat(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return dateTime.format(formatter);
    }
    //
    
    public String banLabel(){
        LoaiBan lb = loaiBan_dao.getLoaiBanTheoMa(ban.getLoaiBan().getMaLB());
        return "Bàn " + ban.getSoBan() + " / " + lb.getTenLB();
    }
    
    public void loadHoaDon(){
        khachVangLaiCheck.setSelected(true);
        setHoaDon();
        setBan();
        tableLabel.setText(banLabel());
        tongTienLabel.setText(currencyFormat(hd.getTongTien()));
        thoiGianVaoLabel.setText(formatLocalDateTime(hd.getGioVao()));
        DefaultTableModel df = (DefaultTableModel) orderTable.getModel();
        df.setRowCount(0);
        int n = 0;
        for(ChiTietHoaDon ct : list){
            MonAn ma = monAn_dao.getMonAnTheoMa(ct.getMonAn().getMaMA());
            df.addRow(new Object[] {++n ,ma.getTenMA(), ct.getSoLuong(),currencyFormat(ct.getGiaSauGiam()), currencyFormat(ct.getThanhTien())});
        }
        phiPhatSinh.setText(currencyFormat(tinhPhiPhatSinh()));
        tongTienThanhToan.setText(currencyFormat(tinhTongTienThanhToan()));
        int tt = (int)tinhTongTienThanhToan()/1000;
        updateBtn(tt);
        tienThanhToanAm();
    }
    

    
    public int giamGiaThanhVien(){
        LoaiKhachHang loai = loaiKh_dao.TimLoaiKhachHangTim(kh.getLoaiKhachHang().getMaLoaiKH());
        int giamGia = loai.getGiamGiaTV();
        return giamGia;
    }
    
    public double tienCoc(){
        double tienCoc = 0;
        if(ddb != null){
            setHoaDon();
            datBanCheck.setSelected(true);
            tienCoc = ddb.getTienCoc();
        }
        return tienCoc;
    }
    
    public double tinhTongTienThanhToan(){
        double tongTienHang = hd.getTongTien();
        double phiPhatSinh = tinhPhiPhatSinh();
        double tongGiamGia = tongTienGiamGia();
        return tongTienHang + phiPhatSinh - tongGiamGia - tienCoc();
    }
    
    public double vat(){
         return hd.getTongTien() *0.08;
    }

    public double dv(){
        return hd.getTongTien() *0.05;
    }


    
    public double tinhPhiPhatSinh(){
        double vat = vat();
        double phidv = dv();
        double phiPhongVip = 0;
        LoaiBan lb = loaiBan_dao.getLoaiBanTheoMa(ban.getLoaiBan().getMaLB());
        if(lb.getTenLB().equals("Phòng VIP")){
            phiPhongVip = 100000;
        }
        return vat + phidv + phiPhongVip;
    }
    
    
    public double tinhGiamGiaTV(){
        double tongTienHang = hd.getTongTien();
        double giamThanhVien = 0;
        if(kh != null){
            giamThanhVien = tongTienHang * loaiKh_dao.TimLoaiKhachHangTim(kh.getLoaiKhachHang().getMaLoaiKH()).getGiamGiaTV()/100;
        }
        return giamThanhVien;
    }
    

    private double tienKM = 0;
    
    public double tongTienGiamGia(){
        double giamThanhVien = tinhGiamGiaTV();
        double tienKhuyenMai = tienKM;
        
        return giamThanhVien + tienKhuyenMai;
    }
    
    public void tienThanhToanAm(){
        double tienTT = tinhTongTienThanhToan();
        if(tienTT < 0){
            tongTienThanhToan.setText(currencyFormat(0));
            tienThoiKhach.setText(currencyFormat(tienTT * -1));
        }
    }
    
    public double tinhTienThoiKhach(){
        double khThanhToan = 0;
        try{
            khThanhToan = Double.valueOf(khachHangThanhToan.getText()) * 1000;
        }
        catch(Exception e){
            if(tinhTongTienThanhToan() > 0){
                JOptionPane.showMessageDialog(this, "Hãy nhập số tiền thanh toán hợp lệ");
            }
        }
            double tienThoi = khThanhToan - tinhTongTienThanhToan();
            return tienThoi;
    }
    ///////////////////////////////////////
    public void khoiTaoHoaDon() throws JRException{
        double discount1 = 0;
        int stt = 0;
        ArrayList<FieldReportHoaDon> fields = new ArrayList<>();
        for(ChiTietHoaDon ct : list){
            stt ++;
            String name = monAn_dao.getMonAnTheoMa(ct.getMonAn().getMaMA()).getTenMA();
            String qty = String.valueOf(ct.getSoLuong());
            String price = currencyFormat(monAn_dao.getMonAnTheoMa(ct.getMonAn().getMaMA()).getGia());
            String discountPrice = currencyFormat(ct.getGiaSauGiam());
            String total = currencyFormat(ct.getThanhTien());

            fields.add(new FieldReportHoaDon(String.valueOf(stt), name, qty, price, discountPrice, total));
        }
        String orderID = hd.getMaHD();
        String table = banLabel();
        String cashier = nv_dao.getNV(hd.getNhanVien().getMaNV()).getHoTenNV();
        String date = dateFormat(hd.getNgayLap());
        String checkInTime = timeFormat(hd.getGioVao());
        String checkOutTime = timeFormat(hd.getGioRa());
        String customer = "";
        String giamGiaThanhVienPer = "0%";
        String giamGiaThanhVien = currencyFormat(0);
        double giamGiaThanhVien1 = 0;
        String giamGiaSNPer = "0%";
        String giamGiaSN = currencyFormat(0);
        double giamGiaSN1 = 0;
        
        KhachHang kh = null;
        if(hd.getKhachHang() != null){
            kh_dao.findById(hd.getKhachHang().getMaKH());
        }
        
        if(kh != null){
            LoaiKhachHang loaiKh = loaiKh_dao.TimLoaiKhachHangTim(kh.getLoaiKhachHang().getMaLoaiKH());
            customer = kh.getTenKH();
            giamGiaThanhVienPer = loaiKh.getGiamGiaTV()+ "%";
            giamGiaThanhVien = currencyFormat(hd.getTongTien() * loaiKh.getGiamGiaTV()/ 100);
            giamGiaThanhVien1 = hd.getTongTien() * loaiKh.getGiamGiaTV()/ 100;
        }
        String discountName = "";
        String perDiscount = "";
        String discount = "";
        
    

        DonDatBan ddb = null;
        if(hd.getDonDatBan() != null){
            ddb = ddb_dao.findById(hd.getDonDatBan().getMaDDB());
        }
        
        String tienCoc = currencyFormat(0);
        if(ddb != null){
            tienCoc = currencyFormat(ddb.getTienCoc());
        }
        String total = currencyFormat(hd.getTongTien());
        String totalDiscount = currencyFormat(discount1 + hd.getGiamGiaTV());
        String totalPay = currencyFormat(hd.getTongTienTT());
//        DichVu dv = dv_dao.getDV(hd.getDichVu().getMaDV());
        String svc = currencyFormat(hd.getTongTien() * 5/ 100);
        String vat = currencyFormat(hd.getTongTien() * 8/ 100);
        String phongVip = currencyFormat(0);
        Ban ban = ban_dao.getBan(hd.getBan().getMaBan());
        LoaiBan lb = loaiBan_dao.getLoaiBanTheoMa(ban.getLoaiBan().getMaLB());
        if(lb.getTenLB().equals("Phòng VIP")){
            phongVip = currencyFormat(100000);
        }
        ParameterReportHoaDon dataprint = new ParameterReportHoaDon(orderID, table, checkInTime,checkOutTime, date, customer, cashier, total, totalDiscount, svc, vat, totalPay, giamGiaThanhVienPer, giamGiaThanhVien, discount, tienCoc, phongVip,fields);
        ReportManager.getInstance().printReportThanhToanNew(dataprint);
        // load gợi tiền    
    }

    public void updateBtn(int tt){
         List<Integer> listBtn =  predictPayments(tt);
         btn_1.setText(listBtn.get(0) + " K");
         btn_2.setText(listBtn.get(1) + " K");
         btn_3.setText(listBtn.get(2) + " K");
         btn_4.setText(listBtn.get(3) + " K");
         btn_5.setText(listBtn.get(4) + " K");
         btn_6.setText(listBtn.get(5) + " K");
    }
    
    public static List<Integer> predictPayments(int X) {
        int denominations[] = {500,200,100,50,20,10};
        Arrays.sort(denominations); // Sắp xếp mệnh giá từ nhỏ đến lớn
        Set<Integer> payments = new HashSet<>(); // Dùng Set để tránh trùng lặp

        // Thêm các giá trị lớn hơn hoặc bằng X bằng cách cộng thêm từng mệnh giá
        for (int denomination : denominations) {
            int amount = ((X / denomination) + 1) * denomination;
            payments.add(amount);
        }

        // Nếu chưa đủ 6 giá trị, tiếp tục thêm các mệnh giá lớn hơn
        while (payments.size() < 6) {
            List<Integer> tempPayments = new ArrayList<>(payments);
            int maxPayment = tempPayments.get(tempPayments.size() - 1); // Lấy giá trị lớn nhất hiện có
            for (int denomination : denominations) {
                payments.add(maxPayment + denomination);
                if (payments.size() >= 6) {
                    break;
                }
            }
        }

        // Chuyển Set thành List, sắp xếp và lấy 6 giá trị đầu tiên
        List<Integer> sortedPayments = new ArrayList<>(payments);
        sortedPayments.sort(Integer::compareTo);
        return sortedPayments.subList(0, 6);
    }
    
       
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScroll = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        khachVangLaiCheck = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        sdtKhachHang = new javax.swing.JTextField();
        hoTenKH = new javax.swing.JLabel();
        rankKH = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tableLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tongTienLabel = new javax.swing.JLabel();
        tongGiamGia = new javax.swing.JLabel();
        phiPhatSinh = new javax.swing.JLabel();
        tongTienThanhToan = new javax.swing.JLabel();
        khachHangThanhToan = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btn_1 = new javax.swing.JButton();
        btn_2 = new javax.swing.JButton();
        btn_3 = new javax.swing.JButton();
        btn_4 = new javax.swing.JButton();
        btn_5 = new javax.swing.JButton();
        btn_6 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        tienThoiKhach = new javax.swing.JLabel();
        datBanCheck = new javax.swing.JCheckBox();
        thoiGianVaoLabel = new javax.swing.JLabel();
        button1 = new gui.component.Button();
        taoHDBtn = new gui.component.Button();
        gradientPanel1 = new gui.component.GradientPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 730));

        jScroll.setBackground(new java.awt.Color(255, 255, 255));
        jScroll.setPreferredSize(new java.awt.Dimension(500, 680));

        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Món ăn", "SL", "Đơn giá", "Thành tiền"
            }
        ));
        jScroll.setViewportView(orderTable);
        if (orderTable.getColumnModel().getColumnCount() > 0) {
            orderTable.getColumnModel().getColumn(0).setPreferredWidth(5);
            orderTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            orderTable.getColumnModel().getColumn(2).setPreferredWidth(5);
        }

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

        khachVangLaiCheck.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        khachVangLaiCheck.setText("Khách hàng vãng lai");
        khachVangLaiCheck.setEnabled(false);
        khachVangLaiCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khachVangLaiCheckActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setText("Số điện thoại");
        jLabel3.setPreferredSize(new java.awt.Dimension(0, 30));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setText("Họ và tên");
        jLabel4.setPreferredSize(new java.awt.Dimension(0, 30));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel5.setText("Hạng");
        jLabel5.setPreferredSize(new java.awt.Dimension(0, 30));

        sdtKhachHang.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        sdtKhachHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        sdtKhachHang.setPreferredSize(new java.awt.Dimension(71, 30));
        sdtKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sdtKhachHangActionPerformed(evt);
            }
        });

        hoTenKH.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        hoTenKH.setPreferredSize(new java.awt.Dimension(38, 30));

        rankKH.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rankKH.setPreferredSize(new java.awt.Dimension(38, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(khachVangLaiCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sdtKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hoTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rankKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(khachVangLaiCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sdtKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hoTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rankKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel1.setText("THANH TOÁN");

        tableLabel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tableLabel.setText("Bàn");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thanh toán hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel7.setText("Tổng tiền hàng");
        jLabel7.setPreferredSize(new java.awt.Dimension(0, 30));

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel8.setText("Giảm giá");
        jLabel8.setPreferredSize(new java.awt.Dimension(0, 30));

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel10.setText("Phí phát sinh");
        jLabel10.setPreferredSize(new java.awt.Dimension(0, 30));

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel11.setText("Khách cần trả");
        jLabel11.setPreferredSize(new java.awt.Dimension(0, 30));

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel12.setText("Khách thanh toán");
        jLabel12.setPreferredSize(new java.awt.Dimension(0, 30));

        tongTienLabel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tongTienLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tongTienLabel.setText("0");
        tongTienLabel.setPreferredSize(new java.awt.Dimension(120, 30));

        tongGiamGia.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tongGiamGia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tongGiamGia.setText("0");
        tongGiamGia.setPreferredSize(new java.awt.Dimension(120, 30));

        phiPhatSinh.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        phiPhatSinh.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        phiPhatSinh.setText("0");
        phiPhatSinh.setPreferredSize(new java.awt.Dimension(120, 30));

        tongTienThanhToan.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tongTienThanhToan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tongTienThanhToan.setText("0");
        tongTienThanhToan.setPreferredSize(new java.awt.Dimension(120, 30));

        khachHangThanhToan.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        khachHangThanhToan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        khachHangThanhToan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        khachHangThanhToan.setPreferredSize(new java.awt.Dimension(71, 30));
        khachHangThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khachHangThanhToanActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btn_1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btn_1.setText("jButton1");
        btn_1.setBorder(null);
        btn_1.setPreferredSize(new java.awt.Dimension(85, 40));
        btn_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_1ActionPerformed(evt);
            }
        });

        btn_2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btn_2.setText("jButton2");
        btn_2.setBorder(null);
        btn_2.setPreferredSize(new java.awt.Dimension(85, 40));
        btn_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_2ActionPerformed(evt);
            }
        });

        btn_3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btn_3.setText("jButton3");
        btn_3.setBorder(null);
        btn_3.setPreferredSize(new java.awt.Dimension(85, 40));
        btn_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_3ActionPerformed(evt);
            }
        });

        btn_4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btn_4.setText("jButton4");
        btn_4.setBorder(null);
        btn_4.setPreferredSize(new java.awt.Dimension(85, 40));
        btn_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_4ActionPerformed(evt);
            }
        });

        btn_5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btn_5.setText("jButton5");
        btn_5.setBorder(null);
        btn_5.setPreferredSize(new java.awt.Dimension(85, 40));
        btn_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_5ActionPerformed(evt);
            }
        });

        btn_6.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btn_6.setText("jButton6");
        btn_6.setBorder(null);
        btn_6.setPreferredSize(new java.awt.Dimension(85, 40));
        btn_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btn_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btn_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel17.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel17.setText("000 đ");
        jLabel17.setPreferredSize(new java.awt.Dimension(24, 30));

        jLabel18.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel18.setText("Tiền thối khách");
        jLabel18.setPreferredSize(new java.awt.Dimension(0, 30));

        tienThoiKhach.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tienThoiKhach.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tienThoiKhach.setText("0");
        tienThoiKhach.setPreferredSize(new java.awt.Dimension(54, 30));

        datBanCheck.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        datBanCheck.setText("Đặt bàn trước");
        datBanCheck.setDoubleBuffered(true);
        datBanCheck.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tienThoiKhach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tongTienThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(phiPhatSinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tongTienLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tongGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(khachHangThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(datBanCheck)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(datBanCheck)
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tongTienLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tongGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phiPhatSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tongTienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(khachHangThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tienThoiKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        thoiGianVaoLabel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        thoiGianVaoLabel.setText("Ngày");

        button1.setBackground(new java.awt.Color(204, 204, 204));
        button1.setForeground(new java.awt.Color(0, 0, 0));
        button1.setText("In tạm tính");
        button1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        button1.setPreferredSize(new java.awt.Dimension(180, 50));
        button1.setRippleColor(new java.awt.Color(255, 255, 255));
        button1.setShadowColor(new java.awt.Color(255, 255, 255));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        taoHDBtn.setBackground(new java.awt.Color(51, 255, 51));
        taoHDBtn.setForeground(new java.awt.Color(0, 0, 0));
        taoHDBtn.setText("Thanh toán");
        taoHDBtn.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        taoHDBtn.setPreferredSize(new java.awt.Dimension(180, 50));
        taoHDBtn.setRippleColor(new java.awt.Color(255, 255, 255));
        taoHDBtn.setShadowColor(new java.awt.Color(255, 255, 255));
        taoHDBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taoHDBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("X");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout gradientPanel1Layout = new javax.swing.GroupLayout(gradientPanel1);
        gradientPanel1.setLayout(gradientPanel1Layout);
        gradientPanel1Layout.setHorizontalGroup(
            gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        gradientPanel1Layout.setVerticalGroup(
            gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tableLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(taoHDBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(thoiGianVaoLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addComponent(gradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(gradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tableLabel))
                    .addComponent(thoiGianVaoLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(taoHDBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void khachVangLaiCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_khachVangLaiCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_khachVangLaiCheckActionPerformed

    private void sdtKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sdtKhachHangActionPerformed
        // TODO add your handling code here:
        String sdt = sdtKhachHang.getText();
        KhachHang kh = kh_dao.getKHSDT(sdt);
        setKhachHang(kh);
        if(this.kh != null){
            khachVangLaiCheck.setSelected(false);
            hoTenKH.setText(kh.getTenKH());
            rankKH.setText(loaiKh_dao.TimLoaiKhachHangTim(kh.getLoaiKhachHang().getMaLoaiKH()).getTenLoaiKH());
            tongGiamGia.setText(currencyFormat(tongTienGiamGia()));
            tongTienThanhToan.setText(currencyFormat(tinhTongTienThanhToan()));
            
            int tt = (int)(tinhTongTienThanhToan())/1000;
            updateBtn(tt);
        }
        else{
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng");
            hoTenKH.setText("");
            rankKH.setText("");
            tongGiamGia.setText(currencyFormat(tongTienGiamGia()));
            tongTienThanhToan.setText(currencyFormat(tinhTongTienThanhToan()));
            khachVangLaiCheck.setSelected(true);
        }
        tienThanhToanAm();
    }//GEN-LAST:event_sdtKhachHangActionPerformed

    private void taoHDBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taoHDBtnActionPerformed
        // TODO add your handling code here:
        if(ddb!= null){
            hd.setNhanVien(nv);
            hd_dao.update(hd);
            this.hd = hd_dao.getHoaDonTheoMa(maHD);
        }
        String maKh = null;
        if(kh != null){
            maKh = kh.getMaKH();
            
        }

        hd.setKhachHang(kh);
        hd.setTongTienTT(tinhTongTienThanhToan());
        hd.setGiamGiaTV(tinhGiamGiaTV());
        hd.setGioRa(LocalDateTime.now());
        hd.setTrangThai(true);
        if(tinhTienThoiKhach() >= 0 && hd_dao.update(hd) && ban_dao.updateTableState(hd.getBan().getMaBan(), 0)){
            try {
                this.hd = hd_dao.getHoaDonTheoMa(hd.getMaHD());
                ban_dao.updateTableState(hd.getBan().getMaBan(), 0);
                
                if(kh != null){
                    kh_dao.updateDiemLT(kh.getMaKH(),(int)hd.getTongTien()/50000);
                    kh_dao.updateLoaiKH(kh.getMaKH());
                    
                }
                JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                try {
                ReportManager.getInstance().complieReport();
            } catch (JRException ex) {
                ex.printStackTrace();
            }
                khoiTaoHoaDon();
                dashBoard.showPanel(0, 0);
                this.dispose();
            } catch (JRException ex) {
                Logger.getLogger(ThanhToan_Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_taoHDBtnActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
         try {
            ReportManager.getInstance().complieReport();
        } catch (JRException ex) {
            ex.printStackTrace();
        }
        try{
            
            int stt = 0;
            ArrayList<FieldReportDonTamTinh> fields = new ArrayList<>();
            for(ChiTietHoaDon ct : list){
                stt ++;
                fields.add(new FieldReportDonTamTinh(String.valueOf(stt), monAn_dao.getMonAnTheoMa(ct.getMonAn().getMaMA()).getTenMA(), String.valueOf(ct.getSoLuong()), currencyFormat(ct.getGiaSauGiam()), currencyFormat(ct.getThanhTien())));
            }
            ParameterReportDonTamTinh dataprint = new ParameterReportDonTamTinh(banLabel(), dateFormat(hd.getNgayLap()), nv_dao.getNV(hd.getNhanVien().getMaNV()).getHoTenNV(), currencyFormat(hd.getTongTien()), fields);
            ReportManager.getInstance().printReportTamTinh(dataprint);
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_button1ActionPerformed

    private void btn_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_4ActionPerformed
       String tt = suaKien(btn_4.getText());
       khachHangThanhToan.setText(tt);
       khachHangThanhToan.requestFocus();
       tinhTienThoiKhach();
    }//GEN-LAST:event_btn_4ActionPerformed

    private void khachHangThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_khachHangThanhToanActionPerformed
        // TODO add your handling code here:
        if(tinhTienThoiKhach() >= 0){
            tienThoiKhach.setText(currencyFormat(tinhTienThoiKhach()));
        }
        else{
            JOptionPane.showMessageDialog(this, "Số tiền chưa hợp lệ");
            tienThoiKhach.setText("0");
        }
    }//GEN-LAST:event_khachHangThanhToanActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        setVisible(false);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void btn_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_1ActionPerformed
       String tt = suaKien(btn_1.getText());
       khachHangThanhToan.setText(tt);
       khachHangThanhToan.requestFocus();
       tinhTienThoiKhach();
    }//GEN-LAST:event_btn_1ActionPerformed

    private void btn_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_2ActionPerformed
       String tt = suaKien(btn_2.getText());
       khachHangThanhToan.setText(tt);
       khachHangThanhToan.requestFocus();
       tinhTienThoiKhach();
    }//GEN-LAST:event_btn_2ActionPerformed

    private void btn_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_3ActionPerformed
       String tt = suaKien(btn_3.getText());
       khachHangThanhToan.setText(tt);// TODO add your handling code here:
       khachHangThanhToan.requestFocus();
       tinhTienThoiKhach();
    }//GEN-LAST:event_btn_3ActionPerformed

    private void btn_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_5ActionPerformed
       String tt = suaKien(btn_5.getText());
       khachHangThanhToan.setText(tt);// TODO add your handling code here:
       khachHangThanhToan.requestFocus();
       tinhTienThoiKhach();
    }//GEN-LAST:event_btn_5ActionPerformed

    private void btn_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_6ActionPerformed
       String tt = suaKien(btn_6.getText());
       khachHangThanhToan.setText(tt);
       khachHangThanhToan.requestFocus();
       tinhTienThoiKhach();
    }//GEN-LAST:event_btn_6ActionPerformed

    public String suaKien(String lblButton){
        String tienKhachDua = lblButton.split("\\s+")[0];

        int tienKhachDua_1 = Integer.parseInt(tienKhachDua);

        return tienKhachDua_1 + "";

    }
    
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ThanhToan_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ThanhToan_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ThanhToan_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ThanhToan_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ThanhToan_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ThanhToan_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ThanhToan_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ThanhToan_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ThanhToan_Form().setVisible(true);
//            }
//        });new ThanhToan_Form().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_1;
    private javax.swing.JButton btn_2;
    private javax.swing.JButton btn_3;
    private javax.swing.JButton btn_4;
    private javax.swing.JButton btn_5;
    private javax.swing.JButton btn_6;
    private gui.component.Button button1;
    private javax.swing.JCheckBox datBanCheck;
    private gui.component.GradientPanel gradientPanel1;
    private javax.swing.JLabel hoTenKH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScroll;
    private javax.swing.JTextField khachHangThanhToan;
    private javax.swing.JCheckBox khachVangLaiCheck;
    private javax.swing.JTable orderTable;
    private javax.swing.JLabel phiPhatSinh;
    private javax.swing.JLabel rankKH;
    private javax.swing.JTextField sdtKhachHang;
    private javax.swing.JLabel tableLabel;
    private gui.component.Button taoHDBtn;
    private javax.swing.JLabel thoiGianVaoLabel;
    private javax.swing.JLabel tienThoiKhach;
    private javax.swing.JLabel tongGiamGia;
    private javax.swing.JLabel tongTienLabel;
    private javax.swing.JLabel tongTienThanhToan;
    // End of variables declaration//GEN-END:variables
}
