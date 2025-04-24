/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.form;

import dao.BanDAO;
import dao.ChiTietDatBanDAO;
import dao.DonDatBanDAO;
import dao.KhachHangDAO;
import dao.LoaiBanDAO;
import dao.LoaiMonAnDAO;
import dao.MonAnDAO;
import dao.NhanVienDAO;
import dao.impl.BanDAOImpl;
import dao.impl.ChiTietDatBanDAOImpl;
import dao.impl.DonDatBanDAOImpl;
import dao.impl.KhachHangDAOImpl;
import dao.impl.LoaiBanDAOImpl;
import dao.impl.LoaiMonAnDAOImpl;
import dao.impl.MonAnDAOImpl;
import dao.impl.NhanVienDAOImpl;
import gui.component.ItemMonAnDatBan;
import gui.component.ItemTable;
import gui.swing.WrapLayout;
import gui.swing.table.TableCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import gui.main.QuanLy_DashBoard;
import gui.main.LeTan_DashBoard;
import gui.swing.table.ScrollBarCustomUI;
import gui.swing.table.cell.EventCellInputChange;
import gui.swing.table.cell.SpinnerCellEditor;
import gui.swing.table.cell.SpinnerCellRenderer;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.Ban;
import model.ChiTietDatBan;
import model.DonDatBan;
import model.KhachHang;
import model.LoaiBan;
import model.LoaiMonAn;
import model.MonAn;
import model.NhanVien;

/**
 *
 * @author Thanh Tuan
 */
public final class DatBan_PN extends javax.swing.JPanel {

    private NhanVienDAO nhanVienDAO = new NhanVienDAOImpl(NhanVien.class);
    private MonAnDAO monAnDAO = new MonAnDAOImpl(MonAn.class);
    private BanDAO banDAO = new BanDAOImpl(Ban.class);
    private LoaiMonAnDAO loaiMonAnDAO = new LoaiMonAnDAOImpl(LoaiMonAn.class);
    private LoaiBanDAO loaiBanDAO = new LoaiBanDAOImpl(LoaiBan.class);
    private DonDatBanDAO donDatBanDAO = new DonDatBanDAOImpl(DonDatBan.class);
    private KhachHangDAO khachHangDAO = new KhachHangDAOImpl(KhachHang.class);
    private ChiTietDatBanDAO chiTietDatBanDAO = new ChiTietDatBanDAOImpl(ChiTietDatBan.class);

    private NhanVien nv;
    private LocalDateTime gioHen;
    private DefaultTableModel tableModel;

    /**
     * Creates new form TaoHoaDon_PN
     */
    public DatBan_PN(QuanLy_DashBoard dashBoard) {
        initComponents();
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
        init();
        this.nv = nhanVienDAO.findById(dashBoard.getHeader().getTextMaNV());
    }

    public DatBan_PN(LeTan_DashBoard dashBoard) {
        initComponents();
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
        init();
        this.nv = nhanVienDAO.findById(dashBoard.getHeader().getTextMaNV());
    }

    /**
     * Codes điều chỉnh giao diện
     */
    private void init() {
        txtDate.setDate(new Date());
        timePicker1.setSelectedTime(new Date());
        tongTienLabel.setText(currencyFormat(100000));
        tableModel = (DefaultTableModel) orderTable.getModel();
    }

    public void setWrapLayout() {
        foodsPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 5, 5));
        tablesPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 5, 5));
        loaiMonAnPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 3, 3));
        loaiBanPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 3, 3));
    }

    public void hideIdColumn() {
        TableColumnModel columnModel = orderTable.getColumnModel();
        TableColumn column = columnModel.getColumn(6);
        columnModel.removeColumn(column);
    }

    public void setCellRender() {
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
    public String currencyFormat(double price) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.format(price);
    }

    public double currencyFormatToDouble(String currency) {
        String str = currency.replaceAll("[^\\d]", "");
        return Double.parseDouble(str);
    }

    public String formatLocalDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return dateTime.format(formatter);
    }

    /**
     * Code tính toán
     */
    public void tinhTongTien() {
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

    public void DefaultSelectLoaiBan() {
        Component[] list = loaiBanPanel.getComponents();
        JButton but = (JButton) list[0];
        but.setBackground(Color.ORANGE); // Đặt màu nền cam cho loại bàn mặc định
        for (Component c : list) {
            if (c != but) {
                ((JButton) c).setBackground(Color.WHITE); // Đặt các nút khác thành trắng
            }
        }
        loaiBanPanel.revalidate();
        loaiBanPanel.repaint();
        loadBanTheoLoai(but.getToolTipText());
    }

    public void loadLoaiMon() {
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

    public void loadLoaiBan() {
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

    public void addEventToLoaiMonButton(JButton button) {
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

    public void addEventToLoaiBanButton(JButton button) {
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

    public void loadMonTheoLoai(String maLoai) {
        List<MonAn> listMA = monAnDAO.danhSachMonAnTheoMaLoai(maLoai);
        foodsPanel.removeAll(); // Xóa tất cả các thành phần
        foodsPanel.revalidate(); // Cập nhật lại bố cục
        foodsPanel.repaint(); // Vẽ lại giao diện
        for (MonAn ma : listMA) {
            foodsPanel.add(new ItemMonAnDatBan(ma, orderTable, tongTienLabel));
        }
    }

    public void loadBanTheoLoai(String maLoai) {
        List<Ban> listB = banDAO.danhSachBanTheoMaLoai(maLoai);
        tablesPanel.removeAll(); // Xóa tất cả các thành phần
        tablesPanel.revalidate(); // Cập nhật lại bố cục
        tablesPanel.repaint(); // Vẽ lại giao diện
        for (Ban b : listB) {
            tablesPanel.add(new ItemTable(tableLable, b));
        }
    }

    public void batSuKienTable() {
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
        // Tính thời gian hiện tại cộng thêm 4 tiếng
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime thoiGianDatToiThieu = currentTime.plusHours(4);

        if (txtTime.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn giờ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Giờ đặt 
        String ngayDat = new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate());
        String gioDat = txtTime.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        gioHen = LocalDateTime.parse(ngayDat + " " + gioDat, formatter);

        if (donDatBanDAO.checkTimeBan(tableLable.getToolTipText(), gioHen)) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn bàn khác, vì đã có đơn đặt bàn trong khoảng thời gian này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            tableLable.setText("");
            tableLable.setToolTipText("");
            loadBanTrong();
            return false;
        }

        // Check giờ đặt phải sau 4 tiếng
        if (gioHen.isBefore(thoiGianDatToiThieu)) {
            JOptionPane.showMessageDialog(null, "Giờ hẹn phải sau 4 tiếng so với thời gian hiện tại", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        LocalDate maxNH = LocalDate.now().plusDays(30);
        LocalDate ngayHen = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate()));
        if (ngayHen.isBefore(LocalDate.now()) || ngayHen.isAfter(maxNH)) {
            JOptionPane.showMessageDialog(null, "Ngày hẹn chỉ được đặt trong vòng 30 ngày kể từ ngày hiện tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
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

    private String getSelectedLoaiBan() {
        for (Component c : loaiBanPanel.getComponents()) {
            JButton btn = (JButton) c;
            if (btn.getBackground().equals(Color.ORANGE)) {
                return btn.getToolTipText();
            }
        }
        return null;
    }

    public String generateMaDDB(int soThuTu) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        String ngayHT = LocalDateTime.now().format(formatter);
        return "DB" + ngayHT + String.format("%03d", soThuTu);
    }

    private void taoDDB() {
        int stt = donDatBanDAO.findSoThuTuHomNay();
        String maDDB = generateMaDDB(stt);
        String tenKH = txtKH.getText();
        String sdt = txtSDT.getText();
        String ghiChu = txtGhiChu.getText();
        String maBan = tableLable.getToolTipText();
        int slKH = banDAO.findById(maBan).getSoGhe();
        String maNV = nv.getMaNV();
        double tienCoc = currencyFormatToDouble(tongTienLabel.getText());
        DonDatBan ddb = new DonDatBan(maDDB, tenKH, sdt, slKH, tienCoc, null, 0, gioHen, ghiChu, LocalDate.now(), 0, new NhanVien(maNV), new Ban(maBan));

        if (donDatBanDAO.save(ddb)) {
            if (tableModel.getRowCount() > 0) {
                for (int i = 0; i < orderTable.getRowCount(); i++) {
                    String maMA = (String) tableModel.getValueAt(i, 6);
                    double thanhTien = currencyFormatToDouble((String) tableModel.getValueAt(i, 4));
                    int soLuong = (int) tableModel.getValueAt(i, 1);
                    double giaSauGiam = currencyFormatToDouble((String) tableModel.getValueAt(i, 3));
                    MonAn ma = monAnDAO.findById(maMA);
                    chiTietDatBanDAO.save(new ChiTietDatBan(ddb, ma, soLuong, thanhTien, giaSauGiam));
                }
            }
            JOptionPane.showMessageDialog(this, "Tạo đơn đặt bàn thành công");
            refresh();
        } else {
            JOptionPane.showMessageDialog(this, "Tạo đơn đặt bàn không thành công");
        }
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

    private void refresh() {
        txtKH.setText("");
        txtSDT.setText("");
        txtGhiChu.setText("");
        tableLable.setText("");
        tableLable.setToolTipText("");
        tableModel.setRowCount(0);
        txtDate.setDate(new Date());
        timePicker1.setSelectedTime(new Date());
        tongTienLabel.setText(currencyFormat(100000));
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
        tabbedPaneCustom1 = new gui.component.TabbedPaneCustom();
        jPanel3 = new javax.swing.JPanel();
        tablesJScrollPane = new javax.swing.JScrollPane();
        tablesPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        loaiBanPanel = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        foodsJScrollPane = new javax.swing.JScrollPane();
        foodsPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        loaiMonAnPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tableLable = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDate = new com.toedter.calendar.JDateChooser();
        txtTime = new javax.swing.JTextField();
        button3 = new gui.component.Button();
        kiemTraThoiGian = new gui.component.Button();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtKH = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tongTienLabel = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        button2 = new gui.component.Button();
        button1 = new gui.component.Button();

        timePicker1.set24hourMode(true);
        timePicker1.setDisplayText(txtTime);
        timePicker1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(550, 710));

        tabbedPaneCustom1.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        tabbedPaneCustom1.setPreferredSize(new java.awt.Dimension(571, 710));
        tabbedPaneCustom1.setSelectedColor(new java.awt.Color(51, 51, 51));
        tabbedPaneCustom1.setUnselectedColor(new java.awt.Color(153, 153, 153));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tablesJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tablesJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPaneCustom1.addTab("Bàn", jPanel3);

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
                .addComponent(foodsJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE))
        );

        tabbedPaneCustom1.addTab("Món ăn", jPanel32);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(423, 710));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(txtTime, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(kiemTraThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin Liên hệ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel5))
                .addGap(39, 39, 39)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(txtKH)
                    .addComponent(txtSDT))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 500));

        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Món", "SL", "Giá gốc", "Giá sau giảm", "Thành tiền", "Xóa", "id"
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
            orderTable.getColumnModel().getColumn(1).setPreferredWidth(15);
            orderTable.getColumnModel().getColumn(5).setPreferredWidth(5);
            orderTable.getColumnModel().getColumn(6).setResizable(false);
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

        button2.setBackground(new java.awt.Color(50, 50, 50));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icon/icons8-refresh-32.png"))); // NOI18N
        button2.setText("Reset");
        button2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button1.setBackground(new java.awt.Color(50, 50, 50));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icon/icons8-order-32.png"))); // NOI18N
        button1.setText("Đặt Bàn");
        button1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, Short.MAX_VALUE)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

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

    private void txtTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimeActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        int x = button3.getLocationOnScreen().x - 150;
        int y = button3.getLocationOnScreen().y + button3.getHeight();

        timePicker1.showPopup(this, x - this.getLocationOnScreen().x, y - this.getLocationOnScreen().y);
    }//GEN-LAST:event_button3ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        refresh();
    }//GEN-LAST:event_button2ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        if (valid() && checkTime()) {
            int ask = JOptionPane.showConfirmDialog(null, "Bạn có muốn tạo đơn đặt bàn này không?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (ask == JOptionPane.YES_OPTION) {
                taoDDB();
            }
        }
    }//GEN-LAST:event_button1ActionPerformed

    private void txtKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKHActionPerformed

    private void kiemTraThoiGianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kiemTraThoiGianActionPerformed
        if (!checkTime()) {
            return;
        }
        loadBanTrong();
    }//GEN-LAST:event_kiemTraThoiGianActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.component.Button button1;
    private gui.component.Button button2;
    private gui.component.Button button3;
    private javax.swing.JScrollPane foodsJScrollPane;
    private javax.swing.JPanel foodsPanel;
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
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTime;
    // End of variables declaration//GEN-END:variables
}
