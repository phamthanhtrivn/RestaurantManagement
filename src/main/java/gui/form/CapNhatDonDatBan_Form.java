/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.form;

import dao.BanDAO;
import dao.ChiTietDatBanDAO;
import dao.DonDatBanDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.LoaiBanDAO;
import dao.LoaiMonAnDAO;
import dao.MonAnDAO;
import dao.NhanVienDAO;
import dao.impl.BanDAOImpl;
import dao.impl.ChiTietDatBanDAOImpl;
import dao.impl.DonDatBanDAOImpl;
import dao.impl.HoaDonDAOImpl;
import dao.impl.KhachHangDAOImpl;
import dao.impl.LoaiBanDAOImpl;
import dao.impl.LoaiMonAnDAOImpl;
import dao.impl.MonAnDAOImpl;
import dao.impl.NhanVienDAOImpl;
import gui.component.ItemMonAnDatBan;
import gui.component.ItemTable;
import gui.swing.WrapLayout;
import gui.swing.table.ScrollBarCustomUI;
import gui.swing.table.TableCustom;
import gui.swing.table.cell.DeleteLabel;
import gui.swing.table.cell.EventCellInputChange;
import gui.swing.table.cell.SpinnerCellEditor;
import gui.swing.table.cell.SpinnerCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.Ban;
import model.ChiTietDatBan;
import model.DonDatBan;
import model.HoaDon;
import model.KhachHang;
import model.KhuyenMai;
import model.LoaiBan;
import model.LoaiMonAn;
import model.MonAn;
import model.NhanVien;

/**
 *
 * @author THANHTRI
 */
public class CapNhatDonDatBan_Form extends javax.swing.JFrame {

    private MonAnDAO monAnDAO = new MonAnDAOImpl(MonAn.class);
    private BanDAO banDAO = new BanDAOImpl(Ban.class);
    private LoaiBanDAO loaiBanDAO = new LoaiBanDAOImpl(LoaiBan.class);
    private LoaiMonAnDAO loaiMonAnDAO = new LoaiMonAnDAOImpl(LoaiMonAn.class);
    private ChiTietDatBanDAO chiTietDatBanDAO = new ChiTietDatBanDAOImpl(ChiTietDatBan.class);
    private DonDatBanDAO donDatBanDAO = new DonDatBanDAOImpl(DonDatBan.class);
    private NhanVienDAO nhanVienDAO = new NhanVienDAOImpl(NhanVien.class);
    private KhachHangDAO khachHangDAO = new KhachHangDAOImpl(KhachHang.class);
    private HoaDonDAO hoaDonDAO = new HoaDonDAOImpl(HoaDon.class);
    private LocalDateTime gioHen;
    private LoaiBan lb;
    private Ban ban;
    private DefaultTableModel tableModel;
    private DonDatBan ddb;
    private Point mousePressLocation;

    public CapNhatDonDatBan_Form() {
        initComponents();
        setLocationRelativeTo(null);
        customTable();
        setWrapLayout();
        hideIdColumn();
        setCellRender();
        batSuKienTable();
        customItemPane();
        loadLoaiMon();
        loadLoaiBan();
        DefaultSelectLoaiBan();
        DefaultSelectedLoaiMon();
    }

    public CapNhatDonDatBan_Form(DonDatBan ddb) {
        initComponents();
        setLocationRelativeTo(null);
        customTable();
        setWrapLayout();
        hideIdColumn();
        setCellRender();
        batSuKienTable();
        customItemPane();
        loadLoaiMon();
        loadLoaiBan();
        this.ddb = ddb;
        init();
        DefaultSelectLoaiBan();
        DefaultSelectedLoaiMon();
    }

    private void init() {
        tableModel = (DefaultTableModel) orderTable.getModel();
        NhanVien nhanVien = nhanVienDAO.findById(ddb.getNhanVien().getMaNV());
        ban = banDAO.findById(ddb.getBan().getMaBan());
        lb = loaiBanDAO.findById(ban.getLoaiBan().getMaLB());
        List<ChiTietDatBan> list = chiTietDatBanDAO.getListByMaDDB(ddb.getMaDDB());
        this.gioHen = ddb.getGioHen();

        txtNow.setText(new SimpleDateFormat("HH:mm").format(new Date()));
        txtMaDDB.setText(ddb.getMaDDB());
        txtTenNV.setText(nhanVien.getHoTenNV());
        txtKH.setText(ddb.getHoTenKH());
        tableLable.setText("Bàn " + ban.getSoBan() + " - " + lb.getTenLB());
        tableLable.setToolTipText(ddb.getBan().getMaBan());
        txtSDT.setText(ddb.getSoDT());
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        txtDate.setDate(Date.from(ddb.getGioHen().atZone(ZoneId.systemDefault()).toInstant()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        txtTime.setText(ddb.getGioHen().format(formatter));
        timePicker1.setSelectedTime(java.util.Date.from(ddb.getGioHen().atZone(java.time.ZoneId.systemDefault()).toInstant()));
        txtGhiChu.setText(ddb.getGhiChu());

        if (!list.isEmpty()) {
            for (ChiTietDatBan x : list) {
                MonAn ma = monAnDAO.findById(x.getMonAn().getMaMA());
                double giaGiam = tinhGiaGiam(ma.getKhuyenMai(), ma.getGia(), ma);
                double thanhTien = tinhThanhTien(giaGiam, 1);
                tableModel.addRow(new Object[]{ma.getTenMA(), x.getSoLuong(), currencyFormat(ma.getGia()), currencyFormat(giaGiam), currencyFormat(thanhTien), new DeleteLabel().createDeleteLabel(orderTable), ma.getMaMA()});
            }
        }
        tongTienLabel.setText(currencyFormat(ddb.getTienCoc()));
    }

    private double tinhGiaGiam(KhuyenMai km, double price, MonAn ma) {
        if (km == null) {
            return price;
        }
        double soTienGiam = ma.getGia() - (km.getGiamGia() * (10 / 100.0));
        return soTienGiam;
    }

    private double tinhThanhTien(double price, int sl) {
        return price * sl;
    }

    private void setWrapLayout() {
        foodsPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 5, 5));
        tablesPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 5, 5));
        loaiMonAnPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 3, 3));
        loaiBanPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 3, 3));
    }

    private void hideIdColumn() {
        TableColumnModel columnModel = orderTable.getColumnModel();
        TableColumn column = columnModel.getColumn(6);
        columnModel.removeColumn(column);
    }

    private void setCellRender() {
        orderTable.getColumnModel().getColumn(1).setCellRenderer(new SpinnerCellRenderer());
        orderTable.getColumnModel().getColumn(1).setCellEditor(new SpinnerCellEditor(new EventCellInputChange() {
            @Override
            public void inputChanged() {
                tinhTongTien();
            }
        }));
        orderTable.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public void setValue(Object value) {
                if (value instanceof JLabel) {
                    JLabel lbl = (JLabel) value;
                    setIcon(lbl.getIcon());  // Thiết lập icon từ JLabel
                    setText("");  // Bỏ text để chỉ hiển thị icon
                } else {
                    super.setValue(value);  // Nếu không phải JLabel, hiển thị bình thường
                }
            }
        });
    }

    private void customItemPane() {
        tablesJScrollPane.getVerticalScrollBar().setUI(new ScrollBarCustomUI());
        tablesJScrollPane.getHorizontalScrollBar().setUI(new ScrollBarCustomUI());
        foodsJScrollPane.getVerticalScrollBar().setUI(new ScrollBarCustomUI());
        foodsJScrollPane.getHorizontalScrollBar().setUI(new ScrollBarCustomUI());
    }

    private void customTable() {
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        orderTable.getTableHeader().setFont(new Font("Sanserif", Font.BOLD, 12));
        orderTable.getTableHeader().setBackground(new Color(50, 50, 50));
        orderTable.repaint();
    }

    /**
     * Codes format tiền tệ & ngày giờ
     */
    private String currencyFormat(double price) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.format(price);
    }

    private double currencyFormatToDouble(String currency) {
        String str = currency.replaceAll("[^\\d]", "");
        return Double.parseDouble(str);
    }

    private String formatLocalDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return dateTime.format(formatter);
    }

    /**
     * Code tính toán
     */
    private void tinhTongTien() {
        double tongTien = 100000;
        DefaultTableModel df = (DefaultTableModel) orderTable.getModel();
        for (int i = 0; i < df.getRowCount(); i++) {
            tongTien += currencyFormatToDouble((String) df.getValueAt(i, 4)) * 0.3;
        }
        tongTienLabel.setText(currencyFormat(tongTien));
    }

    /*
    * Code xử lý
     */
    public void DefaultSelectedLoaiMon() {
        Component[] list = loaiMonAnPanel.getComponents();
        JButton but = (JButton) list[0];
        but.setBackground(Color.ORANGE); // Đặt màu nền cam cho loại bàn mặc định
        for (Component c : list) {
            if (c != but) {
                ((JButton) c).setBackground(Color.WHITE); // Đặt các nút khác thành trắng
            }
        }
        loaiBanPanel.revalidate();
        loaiBanPanel.repaint();
        loadMonTheoLoai(but.getToolTipText());
    }

    private void DefaultSelectLoaiBan() {
        Component[] list = loaiBanPanel.getComponents();
        String maLB = lb.getMaLB();
        for (Component c : list) {
            JButton btn = (JButton) c;
            if (btn.getToolTipText().equals(maLB)) {
                btn.setBackground(Color.ORANGE); // Đặt màu cam cho nút được chọn
            } else {
                btn.setBackground(Color.WHITE); // Các nút còn lại màu trắng
            }
        }
        loaiBanPanel.revalidate();
        loaiBanPanel.repaint();
        loadBanTheoLoai(lb.getMaLB());
        loadBanTrong();
    }

    private void loadLoaiMon() {
        List<LoaiMonAn> list = loaiMonAnDAO.getAll();
        for (LoaiMonAn loai : list) {
            JButton but = new JButton();
            but.setBackground(Color.WHITE);
            but.setText(loai.getTenLoaiMA());
            but.setToolTipText(loai.getMaLoaiMA());
            but.setBorder(null);
            but.setBorderPainted(false);
            but.setFocusPainted(false);
            but.setPreferredSize(new Dimension(100, 40));
            addEventToLoaiMonButton(but);
            loaiMonAnPanel.add(but);
        }
    }

    private void loadLoaiBan() {
        List<LoaiBan> list = loaiBanDAO.getAll();
        for (LoaiBan loai : list) {
            JButton but = new JButton();
            but.setBackground(Color.WHITE);
            but.setText(loai.getTenLB());
            but.setToolTipText(loai.getMaLB());
            but.setBorder(null);
            but.setBorderPainted(false);
            but.setFocusPainted(false);
            but.setPreferredSize(new Dimension(100, 40));
            addEventToLoaiBanButton(but);
            loaiBanPanel.add(but);
        }
    }

    private void addEventToLoaiMonButton(JButton button) {
        button.addActionListener(e -> {
            Component[] list = loaiMonAnPanel.getComponents();
            button.setBackground(Color.ORANGE);
            for (Component c : list) {
                JButton but = (JButton) c;
                if (but != button) {
                    but.setBackground(Color.WHITE);
                }
            }
            button.setBackground(Color.ORANGE);
            loadMonTheoLoai(button.getToolTipText());
        });
    }

    private void addEventToLoaiBanButton(JButton button) {
        button.addActionListener(e -> {
            Component[] list = loaiBanPanel.getComponents();
            button.setBackground(Color.ORANGE);
            for (Component c : list) {
                JButton but = (JButton) c;
                if (but != button) {
                    but.setBackground(Color.WHITE);
                }
            }
            button.setBackground(Color.ORANGE);
            loadBanTheoLoai(button.getToolTipText());
        });
    }

    private void loadMonTheoLoai(String maLoai) {
        List<MonAn> listMA = monAnDAO.danhSachMonAnTheoMaLoai(maLoai);
        foodsPanel.removeAll(); // Xóa tất cả các thành phần
        foodsPanel.revalidate(); // Cập nhật lại bố cục
        foodsPanel.repaint(); // Vẽ lại giao diện
        for (MonAn ma : listMA) {
            foodsPanel.add(new ItemMonAnDatBan(ma, orderTable, tongTienLabel));
        }
    }

    private void loadBanTheoLoai(String maLoai) {
        List<Ban> listBan = banDAO.danhSachBanTheoMaLoai(maLoai);
        tablesPanel.removeAll(); // Xóa tất cả các thành phần
        tablesPanel.revalidate(); // Cập nhật lại bố cục
        tablesPanel.repaint(); // Vẽ lại giao diện
        for (Ban b : listBan) {
            tablesPanel.add(new ItemTable(tableLable, b));
        }
    }

    private void batSuKienTable() {
        // Bắt sự kiện nhấp chuột vào bảng
        orderTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = orderTable.getSelectedRow();
                int column = orderTable.getSelectedColumn();
                DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
                // Kiểm tra xem người dùng có nhấp vào cột chứa icon không
                if (column == 5 && row >= 0) {
                    int ask = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa món ăn này không?", "Thông báo", JOptionPane.YES_NO_OPTION);
                    if (ask == JOptionPane.YES_OPTION) {
                        model.removeRow(row); // Xóa hàng trong model
                        tinhTongTien(); // Cập nhật lại tổng tiền sau khi xóa
                        orderTable.setEnabled(true);
                    }
                }

            }
        });
    }

    private boolean valid() {
        if (txtSDT.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại khách hàng không được rỗng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!txtSDT.getText().matches("^(0[3|5|7|8|9])[0-9]{8}$")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại khách hàng không hợp lý!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtKH.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Tên khách hàng không được rỗng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (tableLable.getToolTipText() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn bàn!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean checkTime() {
        LocalDateTime current = LocalDateTime.now();

        String ngayDat = new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate());
        String gioDat = txtTime.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        gioHen = LocalDateTime.parse(ngayDat + " " + gioDat, formatter);

        if (current.isAfter(ddb.getGioHen().minusHours(3))) {
            JOptionPane.showMessageDialog(null, "Đơn đặt bàn chỉ được cập nhật trước 3 tiếng so với giờ hẹn!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (gioHen.isBefore(current.plusHours(4))) {
            JOptionPane.showMessageDialog(null, "Giờ hẹn mới phải sau 4 tiếng so với thời gian hiện tại", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        LocalDate maxNH = LocalDate.now().plusDays(30);
        LocalDate ngayHen = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate()));
        if (ngayHen.isBefore(LocalDate.now()) || ngayHen.isAfter(maxNH)) {
            JOptionPane.showMessageDialog(null, "Ngày hẹn chỉ được nằm trong vòng 30 ngày kể từ ngày hiện tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        LocalTime start = LocalTime.of(11, 0);
        LocalTime end = LocalTime.of(21, 0);
        LocalTime gH = LocalTime.parse(txtTime.getText());
        if (gH.isBefore(start) || gH.isAfter(end)) {
            JOptionPane.showMessageDialog(null, "Giờ hẹn chỉ được đặt từ 11h - 21h!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private void updateDDB() {
        String maDDB = txtMaDDB.getText();
        String tenKH = txtKH.getText();
        int sl = ban.getSoGhe();
        String sdt = txtSDT.getText();
        double tienCoc = currencyFormatToDouble(tongTienLabel.getText());
        gioHen = LocalDateTime.parse(new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate()) + " " + txtTime.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String ghiChu = txtGhiChu.getText();
        String maBan = tableLable.getToolTipText();
        DonDatBan donDatBan = donDatBanDAO.findById(maDDB);
        donDatBan.setHoTenKH(tenKH);
        donDatBan.setSoDT(sdt);
        donDatBan.setSoLuongKH(sl);
        donDatBan.setTienCoc(tienCoc);
        donDatBan.setGioHen(gioHen);
        donDatBan.setGhiChu(ghiChu);
        donDatBan.setBan(new Ban(maBan));
        if (donDatBanDAO.update(donDatBan)) {
            chiTietDatBanDAO.deleteListByMaDDB(maDDB);
            for (int i = 0; i < orderTable.getRowCount(); i++) {
                String maMA = (String) tableModel.getValueAt(i, 6);
                double thanhTien = currencyFormatToDouble((String) tableModel.getValueAt(i, 4));
                int soLuong = (int) tableModel.getValueAt(i, 1);
                double giaSauGiam = currencyFormatToDouble((String) tableModel.getValueAt(i, 3));
                MonAn ma = monAnDAO.findById(maMA);
                chiTietDatBanDAO.save(new ChiTietDatBan(new DonDatBan(maDDB), ma, soLuong, thanhTien, giaSauGiam));
            }
            JOptionPane.showMessageDialog(null, "Cập nhật đơn đặt bàn thành công!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            setVisible(false);

        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật đơn đặt bàn không thành công!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private String getSelectedLoaiBan() {
        for (Component c : loaiBanPanel.getComponents()) {
            JButton btn = (JButton) c;
            if (btn.getBackground().equals(Color.ORANGE)) {
                return btn.getToolTipText();
            }
        }
        return null;
    }

    private void loadBanTrong() {
        String maLoaiBan = getSelectedLoaiBan();

        List<Ban> danhSachBanTheoLoai = banDAO.danhSachBanTheoMaLoai(maLoaiBan);

        List<Ban> availableTable = new ArrayList<>();
        for (Ban x : danhSachBanTheoLoai) {
            if (donDatBanDAO.checkTimeBan(x.getMaBan(), gioHen) != true) {
                availableTable.add(x);
            }
        }

        if (availableTable.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có bàn trống!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        tablesPanel.removeAll(); // Xóa tất cả các thành phần
        tablesPanel.revalidate(); // Cập nhật lại bố cục
        tablesPanel.repaint(); // Vẽ lại giao diện

        availableTable.forEach(ban -> tablesPanel.add(new ItemTable(tableLable, ban)));
        tablesPanel.revalidate();
        tablesPanel.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timePicker1 = new com.raven.swing.TimePicker();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        tabbedPaneCustom1 = new gui.component.TabbedPaneCustom();
        jPanel5 = new javax.swing.JPanel();
        tablesJScrollPane = new javax.swing.JScrollPane();
        tablesPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        loaiBanPanel = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        foodsJScrollPane = new javax.swing.JScrollPane();
        foodsPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        loaiMonAnPanel = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tongTienLabel = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        button4 = new gui.component.Button();
        button2 = new gui.component.Button();
        button5 = new gui.component.Button();
        jPanel8 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tableLable = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDate = new com.toedter.calendar.JDateChooser();
        txtTime = new javax.swing.JTextField();
        button3 = new gui.component.Button();
        kiemTraThoiGian = new gui.component.Button();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtKH = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        gradientPanel1 = new gui.component.GradientPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaDDB = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNow = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        timePicker1.set24hourMode(true);
        timePicker1.setDisplayText(txtTime);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(550, 710));

        tabbedPaneCustom1.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        tabbedPaneCustom1.setPreferredSize(new java.awt.Dimension(571, 710));
        tabbedPaneCustom1.setSelectedColor(new java.awt.Color(51, 51, 51));
        tabbedPaneCustom1.setUnselectedColor(new java.awt.Color(204, 204, 204));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        tablesPanel.setPreferredSize(new java.awt.Dimension(581, 600));

        javax.swing.GroupLayout tablesPanelLayout = new javax.swing.GroupLayout(tablesPanel);
        tablesPanel.setLayout(tablesPanelLayout);
        tablesPanelLayout.setHorizontalGroup(
            tablesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
        );
        tablesPanelLayout.setVerticalGroup(
            tablesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        tablesJScrollPane.setViewportView(tablesPanel);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(566, 124));

        loaiBanPanel.setBackground(new java.awt.Color(255, 255, 255));
        loaiBanPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Loại bàn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N
        loaiBanPanel.setPreferredSize(new java.awt.Dimension(564, 120));

        javax.swing.GroupLayout loaiBanPanelLayout = new javax.swing.GroupLayout(loaiBanPanel);
        loaiBanPanel.setLayout(loaiBanPanelLayout);
        loaiBanPanelLayout.setHorizontalGroup(
            loaiBanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
        );
        loaiBanPanelLayout.setVerticalGroup(
            loaiBanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 108, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(loaiBanPanel);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tablesJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tablesJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPaneCustom1.addTab("Bàn", jPanel5);

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));

        foodsJScrollPane.setPreferredSize(new java.awt.Dimension(583, 550));

        foodsPanel.setPreferredSize(new java.awt.Dimension(581, 600));

        javax.swing.GroupLayout foodsPanelLayout = new javax.swing.GroupLayout(foodsPanel);
        foodsPanel.setLayout(foodsPanelLayout);
        foodsPanelLayout.setHorizontalGroup(
            foodsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
        );
        foodsPanelLayout.setVerticalGroup(
            foodsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        foodsJScrollPane.setViewportView(foodsPanel);

        jScrollPane3.setPreferredSize(new java.awt.Dimension(566, 124));

        loaiMonAnPanel.setBackground(new java.awt.Color(255, 255, 255));
        loaiMonAnPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Loại món ăn\n", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N
        loaiMonAnPanel.setPreferredSize(new java.awt.Dimension(560, 120));

        javax.swing.GroupLayout loaiMonAnPanelLayout = new javax.swing.GroupLayout(loaiMonAnPanel);
        loaiMonAnPanel.setLayout(loaiMonAnPanelLayout);
        loaiMonAnPanelLayout.setHorizontalGroup(
            loaiMonAnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
        );
        loaiMonAnPanelLayout.setVerticalGroup(
            loaiMonAnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 108, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(loaiMonAnPanel);

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(foodsJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(foodsJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedPaneCustom1.addTab("Món ăn", jPanel32);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(423, 710));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 500));

        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Món", "SL", "Giá gốc", "Giá sau giảm", "Thành tiền", "", "id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orderTable.setPreferredSize(new java.awt.Dimension(400, 510));
        jScrollPane1.setViewportView(orderTable);
        if (orderTable.getColumnModel().getColumnCount() > 0) {
            orderTable.getColumnModel().getColumn(5).setPreferredWidth(15);
        }

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel7.setText("Tổng tiền: ");

        tongTienLabel.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        tongTienLabel.setForeground(new java.awt.Color(255, 51, 51));
        tongTienLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tongTienLabel.setText(".");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(206, 206, 206)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tongTienLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tongTienLabel))
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        button4.setBackground(new java.awt.Color(50, 50, 50));
        button4.setForeground(new java.awt.Color(255, 255, 255));
        button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icon/icons8-receive-32.png"))); // NOI18N
        button4.setText("Nhận Bàn");
        button4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        button2.setBackground(new java.awt.Color(50, 50, 50));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icon/delete.png"))); // NOI18N
        button2.setText("Hủy");
        button2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button5.setBackground(new java.awt.Color(50, 50, 50));
        button5.setForeground(new java.awt.Color(255, 255, 255));
        button5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icon/edit.png"))); // NOI18N
        button5.setText("Cập Nhật");
        button5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin Đặt bàn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setText("Số bàn:");

        tableLable.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel6.setText("Giờ hẹn:");

        txtDate.setDateFormatString("dd/MM/yyyy");
        txtDate.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtDate.setMinimumSize(new java.awt.Dimension(82, 30));
        txtDate.setPreferredSize(new java.awt.Dimension(103, 25));

        txtTime.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimeActionPerformed(evt);
            }
        });

        button3.setBackground(new java.awt.Color(50, 50, 50));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setText("Chọn giờ");
        button3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        kiemTraThoiGian.setBackground(new java.awt.Color(50, 50, 50));
        kiemTraThoiGian.setForeground(new java.awt.Color(255, 255, 255));
        kiemTraThoiGian.setText("Kiểm tra");
        kiemTraThoiGian.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        kiemTraThoiGian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kiemTraThoiGianActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(tableLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(kiemTraThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTime))
                .addGap(18, 18, 18)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6))
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableLable, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(kiemTraThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin Liên hệ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel5.setText("Số điện thoại:");

        txtSDT.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel9.setText("Tên KH:");

        txtKH.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKHActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel8.setText("Ghi chú:");

        txtGhiChu.setColumns(20);
        txtGhiChu.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setRows(5);
        jScrollPane4.setViewportView(txtGhiChu);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel5))
                .addGap(39, 39, 39)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(txtKH)
                    .addComponent(txtSDT))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        gradientPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                gradientPanel1MouseDragged(evt);
            }
        });
        gradientPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                gradientPanel1MousePressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Thông Tin Chi Tiết Đơn Đặt Bàn");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mã đơn:");

        txtMaDDB.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtMaDDB.setForeground(new java.awt.Color(255, 255, 255));
        txtMaDDB.setText("...");

        txtTenNV.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTenNV.setForeground(new java.awt.Color(255, 255, 255));
        txtTenNV.setText("...");

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nhân viên tạo:");

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("X");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        txtNow.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtNow.setForeground(new java.awt.Color(255, 255, 255));
        txtNow.setText("...");

        jLabel14.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Giờ hiện tại:");

        javax.swing.GroupLayout gradientPanel1Layout = new javax.swing.GroupLayout(gradientPanel1);
        gradientPanel1.setLayout(gradientPanel1Layout);
        gradientPanel1Layout.setHorizontalGroup(
            gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(gradientPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaDDB, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNow, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        gradientPanel1Layout.setVerticalGroup(
            gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaDDB)
                    .addComponent(jLabel11)
                    .addComponent(txtTenNV)
                    .addComponent(txtNow)
                    .addComponent(jLabel14))
                .addContainerGap(14, Short.MAX_VALUE))
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(gradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(gradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        setVisible(false);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        if (valid() && checkTime()) {
            int ask = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật đơn đặt bàn này không?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (ask == JOptionPane.YES_OPTION) {
                updateDDB();
            }
        }
    }//GEN-LAST:event_button5ActionPerformed

    private void gradientPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gradientPanel1MousePressed
        mousePressLocation = evt.getPoint();
    }//GEN-LAST:event_gradientPanel1MousePressed

    private void gradientPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gradientPanel1MouseDragged
        Point locationOnScreen = evt.getLocationOnScreen();
//         Di chuyển JPanel
        this.setLocation(
                locationOnScreen.x - mousePressLocation.x - getInsets().left,
                locationOnScreen.y - mousePressLocation.y - getInsets().top
        );
    }//GEN-LAST:event_gradientPanel1MouseDragged

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        LocalDateTime gioNhanBan = ddb.getGioHen().minusMinutes(30);
        if (LocalDateTime.now().isAfter(gioNhanBan)) {
            String maHD = hoaDonDAO.createMaHD();
            if (donDatBanDAO.capNhatTTDDBDaNhanVaTaoHoaDon(ddb.getMaDDB(), maHD)) {
                JOptionPane.showMessageDialog(null, "Nhận bàn thành công!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                setVisible(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Chưa đến giờ nhận bàn", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_button4ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        HuyDonDatBan_Form form = new HuyDonDatBan_Form(txtMaDDB.getText(), this);
        form.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_button2ActionPerformed

    private void txtTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimeActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        int x = button3.getLocationOnScreen().x - 150;
        int y = button3.getLocationOnScreen().y + button3.getHeight();

        timePicker1.showPopup(this, x - this.getLocationOnScreen().x, y - this.getLocationOnScreen().y);
    }//GEN-LAST:event_button3ActionPerformed

    private void kiemTraThoiGianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kiemTraThoiGianActionPerformed
        if (!checkTime()) {
            return;
        }
        loadBanTrong();
    }//GEN-LAST:event_kiemTraThoiGianActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        if (txtSDT.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại khách hàng không được rỗng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!txtSDT.getText().matches("^(0[3|5|7|8|9])[0-9]{8}$")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại khách hàng không hợp lý!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String phone = txtSDT.getText();
        KhachHang khachHang = khachHangDAO.findByPhone(phone);
        if (khachHang != null) {
            txtKH.setText(khachHang.getTenKH());
        }
    }//GEN-LAST:event_txtSDTActionPerformed

    private void txtKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKHActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CapNhatDonDatBan_Form form = new CapNhatDonDatBan_Form();
        form.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.component.Button button2;
    private gui.component.Button button3;
    private gui.component.Button button4;
    private gui.component.Button button5;
    private javax.swing.JScrollPane foodsJScrollPane;
    private javax.swing.JPanel foodsPanel;
    private gui.component.GradientPanel gradientPanel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private gui.component.Button kiemTraThoiGian;
    private javax.swing.JPanel loaiBanPanel;
    private javax.swing.JPanel loaiMonAnPanel;
    private javax.swing.JTable orderTable;
    private gui.component.TabbedPaneCustom tabbedPaneCustom1;
    private javax.swing.JLabel tableLable;
    private javax.swing.JScrollPane tablesJScrollPane;
    private javax.swing.JPanel tablesPanel;
    private com.raven.swing.TimePicker timePicker1;
    private javax.swing.JLabel tongTienLabel;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtKH;
    private javax.swing.JLabel txtMaDDB;
    private javax.swing.JLabel txtNow;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JLabel txtTenNV;
    private javax.swing.JTextField txtTime;
    // End of variables declaration//GEN-END:variables
}
