/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package airport.views;


import airport.controllers.JtablesObserverController;
import airport.controllers.flights.FlightController;
import airport.controllers.flights.FlightControllerPassenger;
import airport.controllers.flights.FlightControllerShowJtables;
import airport.controllers.flights.FlightControllerUpdateJson;
import airport.controllers.locations.LocationController;
import airport.controllers.locations.LocationControllerShowJtables;
import airport.controllers.locations.LocationControllerUpdateJson;
import airport.controllers.passangers.PassengerController;
import airport.controllers.passangers.PassengerControllerShowJtables;
import airport.controllers.passangers.PassengerControllerUpdateJson;
import airport.controllers.planes.PlaneController;
import airport.controllers.planes.PlaneControllerShowJtables;
import airport.controllers.planes.PlaneControllerUpdateJson;
import airport.controllers.utils.Response;
import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author edangulo
 */
public class AirportFrame extends javax.swing.JFrame {

    /**
     * Creates new form AirportFrame
     */
    private int x, y;
    PassengerController PassengerC = new PassengerController();
    
    
    PlaneController PlaneC = new PlaneController();
    LocationController LocationC = new LocationController();
    
    public AirportFrame() throws IOException {
        initComponents();
        
        JtablesObserverController FlightObserver = new  JtablesObserverController(()->{
            Response response = FlightControllerShowJtables.showAllFlights();
            DefaultTableModel model = (DefaultTableModel) ShowFlightsTable.getModel();
            model.setRowCount(0);
            
            ArrayList<Object[]> rows = (ArrayList<Object[]>) response.getObject();
            for (Object[] row : rows) {
                model.addRow(row);
            }
            
            
        });
        FlightController.addObserver(FlightObserver);
        JtablesObserverController PlaneObserver = new  JtablesObserverController(()->{
            Response response = PlaneControllerShowJtables.showAllPlane();
            DefaultTableModel model = (DefaultTableModel) ShowPlanesTable.getModel();
            model.setRowCount(0);
            
            ArrayList<Object[]> rows = (ArrayList<Object[]>) response.getObject();
            for (Object[] row : rows) {
                model.addRow(row);
            }
        });
        PlaneController.addObserver(PlaneObserver);
        JtablesObserverController LocationObserver = new  JtablesObserverController(()->{
            Response response = LocationControllerShowJtables.showAllLocations();
            DefaultTableModel model = (DefaultTableModel) ShowLocationsTable.getModel();
            model.setRowCount(0);
            
            ArrayList<Object[]> rows = (ArrayList<Object[]>) response.getObject();
            for (Object[] row : rows) {
                model.addRow(row);
            }   
        });
        LocationController.addObserver(LocationObserver);

        JtablesObserverController PassengerObserver = new  JtablesObserverController(()->{
            Response response = PassengerControllerShowJtables.showAllPassengers();
            DefaultTableModel model = (DefaultTableModel) ShowPassengerTable.getModel();
            model.setRowCount(0);
            
            ArrayList<Object[]> rows = (ArrayList<Object[]>) response.getObject();
            for (Object[] row : rows) {
                model.addRow(row);
            }
        });
        PassengerController.addObserver(PassengerObserver);
        updateBoxes();
        this.setBackground(new Color(0, 0, 0, 0));
        this.setLocationRelativeTo(null);

        this.generateMonths();
        this.generateDays();
        this.generateHours();
        this.generateMinutes();
        this.blockPanels();
        
    }
    private void updateBoxes(){
        FlightControllerUpdateJson controlerF = new FlightControllerUpdateJson();
        try{
            ArrayList<String> flightIds = controlerF.updateJsonComponent();
            for(String id : flightIds){
                DelayFlightID.addItem(id);
                AddToFightBox.addItem(id);
            }
        } catch (IOException e) {
            System.out.println("hola");
        }
        PlaneControllerUpdateJson controlerP = new PlaneControllerUpdateJson();
        try{
            ArrayList<String> planeIds = controlerP.updateJsonComponent();
            for(String id : planeIds){
                flightRegistrationPlane.addItem(id);
            }
        }catch (Exception e) {
        }
        LocationControllerUpdateJson controlerL = new LocationControllerUpdateJson();
        try{
            ArrayList<String> locationIds = controlerL.updateJsonComponent();
            for(String id : locationIds){
                flightRegistrationDepartureLocation.addItem(id);
                flightRegistrationArrivalLocation.addItem(id);
                flightRegistrationScaleLocation.addItem(id);
            }
        }catch (Exception e) {
        }
        PassengerControllerUpdateJson controlerU = new PassengerControllerUpdateJson();
        try{
            ArrayList<String> userIds = controlerU.updateJsonComponent();
            for(String id : userIds){
                addministrarionUser.addItem(id);
                
            }
        }catch (Exception e) {
        }
        
    }

    private void blockPanels() {
        //9, 11
        for (int i = 1; i < PaneShowAllLocations.getTabCount(); i++) {
            if (i != 9 && i != 11) {
                PaneShowAllLocations.setEnabledAt(i, false);
            }
        }
    }

    private void generateMonths() {
        for (int i = 1; i < 13; i++) {
            PassengerRegistrationBirthdateMonth.addItem("" + i);
            flightRegistrationDepartureMonth.addItem("" + i);
            UpdateInfoMonth.addItem("" + i);
        }
    }

    private void generateDays() {
        for (int i = 1; i < 32; i++) {
            PassagerRegistrationBirthdateDay.addItem("" + i);
            flightRegistrationDepartureDay.addItem("" + i);
            UpdateInfoDay.addItem("" + i);
        }
    }

    private void generateHours() {
        for (int i = 0; i < 24; i++) {
            flightRegistrationDepartureHour.addItem("" + i);
            flightRegistrationArrivalHour.addItem("" + i);
            flightRegistrationScaleHour.addItem("" + i);
            DelayFlightHour.addItem("" + i);
        }
    }

    private void generateMinutes() {
        for (int i = 0; i < 60; i++) {
            flightRegistrationDepartureMinute.addItem("" + i);
            flightRegistrationArrivalMinute.addItem("" + i);
            flightRegistrationScaleMinute.addItem("" + i);
            DelayFlightMinute.addItem("" + i);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new airport.views.PanelRound();
        panelRound2 = new airport.views.PanelRound();
        ExitButton = new javax.swing.JButton();
        PaneShowAllLocations = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        user = new javax.swing.JRadioButton();
        administrator = new javax.swing.JRadioButton();
        addministrarionUser = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        AirplaneRIdTextfield = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        AirplaneRBrandTextfield = new javax.swing.JTextField();
        AirplaneRModelTextfield = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        AirplaneRMaxCapacityTextfield = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        AirplaneRAirlineTextfield = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        CreateAirplaneButton = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        Location_Registration_AirpotID = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        LocationRegistration_AirportName = new javax.swing.JTextField();
        LocationRegistration_AirportCity = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        LocationRegistration_AirportCountry = new javax.swing.JTextField();
        LocationRegistration_AirportLatitude = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        LocationRegistration_AirportLongitude = new javax.swing.JTextField();
        Create_LocationRegistration = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        flightRegistrationId = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        flightRegistrationPlane = new javax.swing.JComboBox<>();
        flightRegistrationDepartureLocation = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        flightRegistrationArrivalLocation = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        flightRegistrationScaleLocation = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        flightRegistrationDepartureDate = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        flightRegistrationDepartureMonth = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        flightRegistrationDepartureDay = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        flightRegistrationDepartureHour = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        flightRegistrationDepartureMinute = new javax.swing.JComboBox<>();
        flightRegistrationArrivalHour = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        flightRegistrationArrivalMinute = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        flightRegistrationScaleHour = new javax.swing.JComboBox<>();
        flightRegistrationScaleMinute = new javax.swing.JComboBox<>();
        CreateFlightButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        UpdateInfoUserId = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        UpdateInfoFirstName = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        UpdateInfoLastName = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        UpdateInfoBirthdate = new javax.swing.JTextField();
        UpdateInfoMonth = new javax.swing.JComboBox<>();
        UpdateInfoDay = new javax.swing.JComboBox<>();
        UpdateInfoPhone = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        UpdateInfoPhoneCode = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        UpdateInfoCountry = new javax.swing.JTextField();
        UpdateInfoButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        AddToFlightUserId = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        AddToFightBox = new javax.swing.JComboBox<>();
        AddToFlightButton = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaMyFlights = new javax.swing.JTable();
        RefreshButton = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ShowPassengerTable = new javax.swing.JTable();
        RefreshShowAllPassengers = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ShowFlightsTable = new javax.swing.JTable();
        RefreshShowFlights = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        RefresShowPlanes = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        ShowPlanesTable = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        DelayFlightHour = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        DelayFlightID = new javax.swing.JComboBox<>();
        jLabel48 = new javax.swing.JLabel();
        DelayFlightMinute = new javax.swing.JComboBox<>();
        FlightDelayButton = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        ShowLocationsTable = new javax.swing.JTable();
        RefreshShowLocations = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        RegistrationCountry = new javax.swing.JLabel();
        RegistrationID = new javax.swing.JLabel();
        RegistrationFirstName = new javax.swing.JLabel();
        RegistrationLastName = new javax.swing.JLabel();
        RegistrationBirthdate = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        PassengerRegistrateCountryPhoneCode = new javax.swing.JTextField();
        PassengerRegistrateID = new javax.swing.JTextField();
        PassengerRegistrateBirthdateYear = new javax.swing.JTextField();
        PassengerRegistrateCountry = new javax.swing.JTextField();
        PassengerRegistrateNumber = new javax.swing.JTextField();
        RegistrationPhone = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        PassengerRegistrateLastName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        PassengerRegistrationBirthdateMonth = new javax.swing.JComboBox<>();
        PassengerRegistrateFirstName = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        PassagerRegistrationBirthdateDay = new javax.swing.JComboBox<>();
        RegisterPassenger = new javax.swing.JButton();
        panelRound3 = new airport.views.PanelRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelRound1.setRadius(40);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelRound2MouseDragged(evt);
            }
        });
        panelRound2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelRound2MousePressed(evt);
            }
        });

        ExitButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        ExitButton.setText("X");
        ExitButton.setBorderPainted(false);
        ExitButton.setContentAreaFilled(false);
        ExitButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(1083, Short.MAX_VALUE)
                .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addComponent(ExitButton)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        panelRound1.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, -1));

        PaneShowAllLocations.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        user.setText("User");
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        jPanel1.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, -1, -1));

        administrator.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        administrator.setText("Administrator");
        administrator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                administratorActionPerformed(evt);
            }
        });
        jPanel1.add(administrator, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 164, -1, -1));

        addministrarionUser.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        addministrarionUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select User" }));
        addministrarionUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addministrarionUserActionPerformed(evt);
            }
        });
        jPanel1.add(addministrarionUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, 130, -1));

        PaneShowAllLocations.addTab("Administration", jPanel1);

        jPanel3.setLayout(null);

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel11.setText("ID:");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(53, 96, 22, 25);

        AirplaneRIdTextfield.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel3.add(AirplaneRIdTextfield);
        AirplaneRIdTextfield.setBounds(180, 93, 130, 31);

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel12.setText("Brand:");
        jPanel3.add(jLabel12);
        jLabel12.setBounds(53, 157, 50, 25);

        AirplaneRBrandTextfield.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel3.add(AirplaneRBrandTextfield);
        AirplaneRBrandTextfield.setBounds(180, 154, 130, 31);

        AirplaneRModelTextfield.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel3.add(AirplaneRModelTextfield);
        AirplaneRModelTextfield.setBounds(180, 213, 130, 31);

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel13.setText("Model:");
        jPanel3.add(jLabel13);
        jLabel13.setBounds(53, 216, 55, 25);

        AirplaneRMaxCapacityTextfield.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel3.add(AirplaneRMaxCapacityTextfield);
        AirplaneRMaxCapacityTextfield.setBounds(180, 273, 130, 31);

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel14.setText("Max Capacity:");
        jPanel3.add(jLabel14);
        jLabel14.setBounds(53, 276, 109, 25);

        AirplaneRAirlineTextfield.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel3.add(AirplaneRAirlineTextfield);
        AirplaneRAirlineTextfield.setBounds(180, 333, 130, 31);

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel15.setText("Airline:");
        jPanel3.add(jLabel15);
        jLabel15.setBounds(53, 336, 70, 25);

        CreateAirplaneButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        CreateAirplaneButton.setText("Create");
        CreateAirplaneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateAirplaneButtonActionPerformed(evt);
            }
        });
        jPanel3.add(CreateAirplaneButton);
        CreateAirplaneButton.setBounds(490, 480, 120, 40);

        PaneShowAllLocations.addTab("Airplane registration", jPanel3);

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel16.setText("Airport ID:");

        Location_Registration_AirpotID.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        Location_Registration_AirpotID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Location_Registration_AirpotIDActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel17.setText("Airport name:");

        LocationRegistration_AirportName.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        LocationRegistration_AirportCity.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        LocationRegistration_AirportCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocationRegistration_AirportCityActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel18.setText("Airport city:");

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel19.setText("Airport country:");

        LocationRegistration_AirportCountry.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        LocationRegistration_AirportLatitude.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel20.setText("Airport latitude:");

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel21.setText("Airport longitude:");

        LocationRegistration_AirportLongitude.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        Create_LocationRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        Create_LocationRegistration.setText("Create");
        Create_LocationRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Create_LocationRegistrationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addGap(80, 80, 80)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LocationRegistration_AirportLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Location_Registration_AirpotID, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LocationRegistration_AirportName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LocationRegistration_AirportCity, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LocationRegistration_AirportCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LocationRegistration_AirportLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(515, 515, 515)
                        .addComponent(Create_LocationRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(515, 515, 515))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel17)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel18)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel19)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel20))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(Location_Registration_AirpotID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(LocationRegistration_AirportName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(LocationRegistration_AirportCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(LocationRegistration_AirportCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(LocationRegistration_AirportLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(LocationRegistration_AirportLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(Create_LocationRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        PaneShowAllLocations.addTab("Location registration", jPanel13);

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel22.setText("ID:");

        flightRegistrationId.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel23.setText("Plane:");

        flightRegistrationPlane.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        flightRegistrationPlane.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plane" }));

        flightRegistrationDepartureLocation.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        flightRegistrationDepartureLocation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location" }));

        jLabel24.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel24.setText("Departure location:");

        flightRegistrationArrivalLocation.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        flightRegistrationArrivalLocation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location" }));

        jLabel25.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel25.setText("Arrival location:");

        jLabel26.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel26.setText("Scale location:");

        flightRegistrationScaleLocation.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        flightRegistrationScaleLocation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location" }));

        jLabel27.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel27.setText("Duration:");

        jLabel28.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel28.setText("Duration:");

        jLabel29.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel29.setText("Departure date:");

        flightRegistrationDepartureDate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel30.setText("-");

        flightRegistrationDepartureMonth.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        flightRegistrationDepartureMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));

        jLabel31.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel31.setText("-");

        flightRegistrationDepartureDay.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        flightRegistrationDepartureDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));

        jLabel32.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel32.setText("-");

        flightRegistrationDepartureHour.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        flightRegistrationDepartureHour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        jLabel33.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel33.setText("-");

        flightRegistrationDepartureMinute.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        flightRegistrationDepartureMinute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        flightRegistrationArrivalHour.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        flightRegistrationArrivalHour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        jLabel34.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel34.setText("-");

        flightRegistrationArrivalMinute.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        flightRegistrationArrivalMinute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        jLabel35.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel35.setText("-");

        flightRegistrationScaleHour.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        flightRegistrationScaleHour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        flightRegistrationScaleMinute.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        flightRegistrationScaleMinute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        CreateFlightButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        CreateFlightButton.setText("Create");
        CreateFlightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateFlightButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(flightRegistrationScaleLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(flightRegistrationArrivalLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(46, 46, 46)
                        .addComponent(flightRegistrationDepartureLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(flightRegistrationId)
                            .addComponent(flightRegistrationPlane, 0, 130, Short.MAX_VALUE))))
                .addGap(45, 45, 45)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(flightRegistrationDepartureDate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(flightRegistrationDepartureMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(flightRegistrationDepartureDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(flightRegistrationDepartureHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(flightRegistrationDepartureMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(flightRegistrationArrivalHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(flightRegistrationArrivalMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(flightRegistrationScaleHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(flightRegistrationScaleMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CreateFlightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(530, 530, 530))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel22))
                    .addComponent(flightRegistrationId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(flightRegistrationPlane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(flightRegistrationDepartureHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(flightRegistrationDepartureMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel24)
                                .addComponent(flightRegistrationDepartureLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel29))
                            .addComponent(flightRegistrationDepartureDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(flightRegistrationDepartureMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31)
                            .addComponent(flightRegistrationDepartureDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel25)
                                .addComponent(flightRegistrationArrivalLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel28))
                            .addComponent(flightRegistrationArrivalHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)
                            .addComponent(flightRegistrationArrivalMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(flightRegistrationScaleHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)
                            .addComponent(flightRegistrationScaleMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel26)
                                .addComponent(flightRegistrationScaleLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel27)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addComponent(CreateFlightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        PaneShowAllLocations.addTab("Flight registration", jPanel4);

        jLabel36.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel36.setText("ID:");

        UpdateInfoUserId.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        UpdateInfoUserId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateInfoUserIdActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel37.setText("First Name:");

        UpdateInfoFirstName.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel38.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel38.setText("Last Name:");

        UpdateInfoLastName.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel39.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel39.setText("Birthdate:");

        UpdateInfoBirthdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        UpdateInfoMonth.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        UpdateInfoMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));

        UpdateInfoDay.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        UpdateInfoDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));

        UpdateInfoPhone.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel40.setText("-");

        UpdateInfoPhoneCode.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel41.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel41.setText("+");

        jLabel42.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel42.setText("Phone:");

        jLabel43.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel43.setText("Country:");

        UpdateInfoCountry.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        UpdateInfoButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        UpdateInfoButton.setText("Update");
        UpdateInfoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateInfoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addGap(108, 108, 108)
                                .addComponent(UpdateInfoUserId, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addGap(41, 41, 41)
                                .addComponent(UpdateInfoFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(43, 43, 43)
                                .addComponent(UpdateInfoLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addGap(55, 55, 55)
                                .addComponent(UpdateInfoBirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(UpdateInfoMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(UpdateInfoDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addGap(56, 56, 56)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(UpdateInfoPhoneCode, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(UpdateInfoPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel43)
                                .addGap(63, 63, 63)
                                .addComponent(UpdateInfoCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(507, 507, 507)
                        .addComponent(UpdateInfoButton)))
                .addContainerGap(586, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(UpdateInfoUserId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(UpdateInfoFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(UpdateInfoLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(UpdateInfoBirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UpdateInfoMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UpdateInfoDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(jLabel41)
                    .addComponent(UpdateInfoPhoneCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(UpdateInfoPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addComponent(UpdateInfoCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(UpdateInfoButton)
                .addGap(113, 113, 113))
        );

        PaneShowAllLocations.addTab("Update info", jPanel5);

        AddToFlightUserId.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        AddToFlightUserId.setEnabled(false);
        AddToFlightUserId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddToFlightUserIdActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel44.setText("ID:");

        jLabel45.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel45.setText("Flight:");

        AddToFightBox.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        AddToFightBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Flight" }));

        AddToFlightButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        AddToFlightButton.setText("Add");
        AddToFlightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddToFlightButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45))
                .addGap(79, 79, 79)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddToFightBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddToFlightUserId, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(860, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AddToFlightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(509, 509, 509))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel44))
                    .addComponent(AddToFlightUserId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(AddToFightBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
                .addComponent(AddToFlightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );

        PaneShowAllLocations.addTab("Add to flight", jPanel6);

        TablaMyFlights.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        TablaMyFlights.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Departure Date", "Arrival Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaMyFlights);

        RefreshButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        RefreshButton.setText("Refresh");
        RefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(322, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RefreshButton)
                .addGap(527, 527, 527))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(RefreshButton)
                .addContainerGap())
        );

        PaneShowAllLocations.addTab("Show my flights", jPanel7);

        ShowPassengerTable.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        ShowPassengerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Birthdate", "Age", "Phone", "Country", "Num Flight"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(ShowPassengerTable);

        RefreshShowAllPassengers.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        RefreshShowAllPassengers.setText("Refresh");
        RefreshShowAllPassengers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshShowAllPassengersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(489, 489, 489)
                        .addComponent(RefreshShowAllPassengers))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1078, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RefreshShowAllPassengers)
                .addContainerGap())
        );

        PaneShowAllLocations.addTab("Show all passengers", jPanel8);

        ShowFlightsTable.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        ShowFlightsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Departure Airport ID", "Arrival Airport ID", "Scale Airport ID", "Departure Date", "Arrival Date", "Plane ID", "Number Passengers"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(ShowFlightsTable);

        RefreshShowFlights.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        RefreshShowFlights.setText("Refresh");
        RefreshShowFlights.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshShowFlightsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(521, 521, 521)
                        .addComponent(RefreshShowFlights)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RefreshShowFlights)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        PaneShowAllLocations.addTab("Show all flights", jPanel9);

        RefresShowPlanes.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        RefresShowPlanes.setText("Refresh");
        RefresShowPlanes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefresShowPlanesActionPerformed(evt);
            }
        });

        ShowPlanesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Brand", "Model", "Max Capacity", "Airline", "Number Flights"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(ShowPlanesTable);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(508, 508, 508)
                        .addComponent(RefresShowPlanes))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(220, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(RefresShowPlanes)
                .addGap(17, 17, 17))
        );

        PaneShowAllLocations.addTab("Show all planes", jPanel10);

        DelayFlightHour.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        DelayFlightHour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));
        DelayFlightHour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DelayFlightHourActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel46.setText("Hours:");

        jLabel47.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel47.setText("ID:");

        DelayFlightID.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        DelayFlightID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID" }));

        jLabel48.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel48.setText("Minutes:");

        DelayFlightMinute.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        DelayFlightMinute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        FlightDelayButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightDelayButton.setText("Delay");
        FlightDelayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FlightDelayButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DelayFlightMinute, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(jLabel46))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DelayFlightHour, 0, 136, Short.MAX_VALUE)
                            .addComponent(DelayFlightID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(820, 820, 820))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FlightDelayButton)
                .addGap(531, 531, 531))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(DelayFlightID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(DelayFlightHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(DelayFlightMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 307, Short.MAX_VALUE)
                .addComponent(FlightDelayButton)
                .addGap(33, 33, 33))
        );

        PaneShowAllLocations.addTab("Delay flight", jPanel12);

        ShowLocationsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Airport ID", "Airport Name", "City", "Country"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(ShowLocationsTable);

        RefreshShowLocations.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        RefreshShowLocations.setText("Refresh");
        RefreshShowLocations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshShowLocationsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(508, 508, 508)
                        .addComponent(RefreshShowLocations))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(303, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(RefreshShowLocations)
                .addGap(17, 17, 17))
        );

        PaneShowAllLocations.addTab("Show all locations", jPanel11);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RegistrationCountry.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        RegistrationCountry.setText("Country:");
        jPanel2.add(RegistrationCountry, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, -1, -1));

        RegistrationID.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        RegistrationID.setText("ID:");
        jPanel2.add(RegistrationID, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        RegistrationFirstName.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        RegistrationFirstName.setText("First Name:");
        jPanel2.add(RegistrationFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        RegistrationLastName.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        RegistrationLastName.setText("Last Name:");
        jPanel2.add(RegistrationLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        RegistrationBirthdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        RegistrationBirthdate.setText("Birthdate:");
        jPanel2.add(RegistrationBirthdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel6.setText("+");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 20, -1));

        PassengerRegistrateCountryPhoneCode.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel2.add(PassengerRegistrateCountryPhoneCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 50, -1));

        PassengerRegistrateID.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassengerRegistrateID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PassengerRegistrateIDActionPerformed(evt);
            }
        });
        jPanel2.add(PassengerRegistrateID, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 130, -1));

        PassengerRegistrateBirthdateYear.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassengerRegistrateBirthdateYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PassengerRegistrateBirthdateYearActionPerformed(evt);
            }
        });
        jPanel2.add(PassengerRegistrateBirthdateYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 90, -1));

        PassengerRegistrateCountry.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel2.add(PassengerRegistrateCountry, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 130, -1));

        PassengerRegistrateNumber.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel2.add(PassengerRegistrateNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 340, 130, -1));

        RegistrationPhone.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        RegistrationPhone.setText("Phone:");
        jPanel2.add(RegistrationPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, -1));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel8.setText("-");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, 30, -1));

        PassengerRegistrateLastName.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassengerRegistrateLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PassengerRegistrateLastNameActionPerformed(evt);
            }
        });
        jPanel2.add(PassengerRegistrateLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 130, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel9.setText("-");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 30, -1));

        PassengerRegistrationBirthdateMonth.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassengerRegistrationBirthdateMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));
        jPanel2.add(PassengerRegistrationBirthdateMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, -1, -1));

        PassengerRegistrateFirstName.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jPanel2.add(PassengerRegistrateFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 130, -1));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel10.setText("-");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, 30, -1));

        PassagerRegistrationBirthdateDay.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassagerRegistrationBirthdateDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));
        PassagerRegistrationBirthdateDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PassagerRegistrationBirthdateDayActionPerformed(evt);
            }
        });
        jPanel2.add(PassagerRegistrationBirthdateDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, -1, -1));

        RegisterPassenger.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        RegisterPassenger.setText("Register");
        RegisterPassenger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterPassengerActionPerformed(evt);
            }
        });
        jPanel2.add(RegisterPassenger, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 480, -1, -1));

        PaneShowAllLocations.addTab("Passenger registration", jPanel2);

        panelRound1.add(PaneShowAllLocations, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 41, 1150, 620));

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        panelRound1.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 650, 1150, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void panelRound2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound2MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_panelRound2MousePressed

    private void panelRound2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound2MouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_panelRound2MouseDragged

    private void administratorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_administratorActionPerformed
        if (user.isSelected()) {
            user.setSelected(false);
            addministrarionUser.setSelectedIndex(0);

        }
        for (int i = 1; i < PaneShowAllLocations.getTabCount(); i++) {
                PaneShowAllLocations.setEnabledAt(i, true);
        }
        PaneShowAllLocations.setEnabledAt(5, false);
        PaneShowAllLocations.setEnabledAt(6, false);
        PaneShowAllLocations.setEnabledAt(4, false);
    }//GEN-LAST:event_administratorActionPerformed

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed
        if (administrator.isSelected()) {
            administrator.setSelected(false);
        }
        for (int i = 1; i < PaneShowAllLocations.getTabCount(); i++) {

            PaneShowAllLocations.setEnabledAt(i, false);

        }
        PaneShowAllLocations.setEnabledAt(4, true);
        PaneShowAllLocations.setEnabledAt(9, true);
        PaneShowAllLocations.setEnabledAt(5, true);
        PaneShowAllLocations.setEnabledAt(6, true);
        PaneShowAllLocations.setEnabledAt(7, true);
        PaneShowAllLocations.setEnabledAt(11, true);
    }//GEN-LAST:event_userActionPerformed

    private void RegisterPassengerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterPassengerActionPerformed
        // TODO add your handling code here:
        String id = PassengerRegistrateID.getText();
        String firstname = PassengerRegistrateFirstName.getText();
        String lastname = PassengerRegistrateLastName.getText();
        String year = PassengerRegistrateBirthdateYear.getText();
        String month = PassengerRegistrationBirthdateMonth.getItemAt(PassengerRegistrationBirthdateMonth.getSelectedIndex());
        String day = PassagerRegistrationBirthdateDay.getItemAt(PassagerRegistrationBirthdateDay.getSelectedIndex());
        String phoneCode = PassengerRegistrateCountryPhoneCode.getText();
        String phone = PassengerRegistrateNumber.getText();
        String country = PassengerRegistrateCountry.getText();
        
        
        Response response = PassengerController.createPassenger(id, firstname,  lastname,  year,  month,  day, phoneCode,  phone,  country);
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        }else{
            PassengerRegistrateID.setText("");
            PassengerRegistrateFirstName.setText("");
            PassengerRegistrateLastName.setText("");
            PassengerRegistrateBirthdateYear.setText("");
            PassengerRegistrateCountryPhoneCode.setText("");
            PassengerRegistrateNumber.setText("");
            PassengerRegistrateCountry.setText("");
            PassengerRegistrationBirthdateMonth.setSelectedIndex(0);
            PassagerRegistrationBirthdateDay.setSelectedIndex(0);
            
            this.addministrarionUser.addItem("" + id);
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
                
    }//GEN-LAST:event_RegisterPassengerActionPerformed

    private void CreateAirplaneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateAirplaneButtonActionPerformed
        // TODO add your handling code here:
        
        String id = AirplaneRIdTextfield.getText();
        String brand = AirplaneRBrandTextfield.getText();
        String model = AirplaneRModelTextfield.getText();
        String maxCapacity = AirplaneRMaxCapacityTextfield.getText();
        String airline = AirplaneRAirlineTextfield.getText();

        Response response = PlaneController.createPlane(id, brand, model, maxCapacity, airline);
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        }else{
            AirplaneRIdTextfield.setText("");
            AirplaneRBrandTextfield.setText("");
            AirplaneRModelTextfield.setText("");
            AirplaneRMaxCapacityTextfield.setText("");
            AirplaneRAirlineTextfield.setText("");
            this.flightRegistrationPlane.addItem(id);
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }

       
    }//GEN-LAST:event_CreateAirplaneButtonActionPerformed

    private void Create_LocationRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Create_LocationRegistrationActionPerformed
        // TODO add your handling code here:
        String id = Location_Registration_AirpotID.getText();
        String name = LocationRegistration_AirportName.getText();
        String city = LocationRegistration_AirportCity.getText();
        String country = LocationRegistration_AirportCountry.getText();
        String latitude = LocationRegistration_AirportLatitude.getText();
        String longitude = LocationRegistration_AirportLongitude.getText();
        
        Response response = LocationController.createLocation(id, name, city, country, latitude, longitude);
        
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        }else{
            Location_Registration_AirpotID.setText("");
            LocationRegistration_AirportName.setText("");
            LocationRegistration_AirportCity.setText("");
            LocationRegistration_AirportCountry.setText("");
            LocationRegistration_AirportLatitude.setText("");
            LocationRegistration_AirportLongitude.setText("");
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
            this.flightRegistrationDepartureLocation.addItem(id);
            this.flightRegistrationArrivalLocation.addItem(id);
            this.flightRegistrationScaleLocation.addItem(id);
        }


        
    }//GEN-LAST:event_Create_LocationRegistrationActionPerformed

    private void CreateFlightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateFlightButtonActionPerformed
        // TODO add your handling code here:
        String id = flightRegistrationId.getText();
        String planeId = flightRegistrationPlane.getItemAt(flightRegistrationPlane.getSelectedIndex());
        String departureLocationId = flightRegistrationDepartureLocation.getItemAt(flightRegistrationDepartureLocation.getSelectedIndex());
        String arrivalLocationId = flightRegistrationArrivalLocation.getItemAt(flightRegistrationArrivalLocation.getSelectedIndex());
        String scaleLocationId = flightRegistrationScaleLocation.getItemAt(flightRegistrationScaleLocation.getSelectedIndex());
        String year = flightRegistrationDepartureDate.getText();
        String month = flightRegistrationDepartureMonth.getItemAt(flightRegistrationDepartureMonth.getSelectedIndex());
        String day = flightRegistrationDepartureDay.getItemAt(flightRegistrationDepartureDay.getSelectedIndex());
        String hour = flightRegistrationDepartureHour.getItemAt(flightRegistrationDepartureHour.getSelectedIndex());
        String minutes = flightRegistrationDepartureMinute.getItemAt(flightRegistrationDepartureMinute.getSelectedIndex());
        String hoursDurationsArrival = flightRegistrationArrivalHour.getItemAt(flightRegistrationArrivalHour.getSelectedIndex());
        String minutesDurationsArrival = flightRegistrationArrivalMinute.getItemAt(flightRegistrationArrivalMinute.getSelectedIndex());
        String hoursDurationsScale = flightRegistrationScaleHour.getItemAt(flightRegistrationScaleHour.getSelectedIndex());
        String minutesDurationsScale = flightRegistrationScaleMinute.getItemAt(flightRegistrationScaleMinute.getSelectedIndex());
        
        Response response = FlightController.createFlight(id, planeId, departureLocationId, arrivalLocationId, scaleLocationId, year, month, day, hour, minutes, hoursDurationsArrival, minutesDurationsArrival, hoursDurationsScale, minutesDurationsScale);
        
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        }else{
            flightRegistrationId.setText("");
            flightRegistrationDepartureDate.setText("");
            
            flightRegistrationPlane.setSelectedIndex(0);
            flightRegistrationDepartureLocation.setSelectedIndex(0);
            flightRegistrationArrivalLocation.setSelectedIndex(0);
            flightRegistrationScaleLocation.setSelectedIndex(0);
            flightRegistrationDepartureMonth.setSelectedIndex(0);
            flightRegistrationDepartureDay.setSelectedIndex(0);
            flightRegistrationDepartureHour.setSelectedIndex(0);
            flightRegistrationDepartureMinute.setSelectedIndex(0);
            flightRegistrationArrivalHour.setSelectedIndex(0);
            flightRegistrationArrivalMinute.setSelectedIndex(0);
            flightRegistrationScaleHour.setSelectedIndex(0);
            flightRegistrationScaleMinute.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
            this.AddToFightBox.addItem(id);
            this.DelayFlightID.addItem(id);
        }

        
    }//GEN-LAST:event_CreateFlightButtonActionPerformed

    private void UpdateInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateInfoButtonActionPerformed
        // TODO add your handling code here:
        String id = UpdateInfoUserId.getText();
        String firstname = UpdateInfoFirstName.getText();
        String lastname = UpdateInfoLastName.getText();
        String year = UpdateInfoBirthdate.getText();
        String month = PassengerRegistrationBirthdateMonth.getItemAt(UpdateInfoMonth.getSelectedIndex());
        String day = PassagerRegistrationBirthdateDay.getItemAt(UpdateInfoDay.getSelectedIndex());
        String phoneCode = UpdateInfoPhoneCode.getText();
        String phone = UpdateInfoPhone.getText();
        String country = UpdateInfoCountry.getText();

        Response response = PassengerController.updateInfo(id, firstname, lastname, year, month, day, phoneCode, phone, country);
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        }else{
            
            UpdateInfoFirstName.setText("");
            UpdateInfoLastName.setText("");
            UpdateInfoBirthdate.setText("");
            UpdateInfoPhoneCode.setText("");
            UpdateInfoPhone.setText("");
            UpdateInfoCountry.setText("");
            PassengerRegistrationBirthdateMonth.setSelectedIndex(0);
            PassagerRegistrationBirthdateDay.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
            
        }
    }//GEN-LAST:event_UpdateInfoButtonActionPerformed

    private void AddToFlightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddToFlightButtonActionPerformed
        // TODO add your handling code here:
        String passengerId = AddToFlightUserId.getText();
        String flightId = AddToFightBox.getItemAt(AddToFightBox.getSelectedIndex());
        Response response =  FlightControllerPassenger.addPassenger(passengerId, flightId);
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        }else{
            
            AddToFightBox.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
            
        }
    }//GEN-LAST:event_AddToFlightButtonActionPerformed

    private void FlightDelayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FlightDelayButtonActionPerformed
        // TODO add your handling code here:
        String flightId = DelayFlightID.getItemAt(DelayFlightID.getSelectedIndex());
        String hours = DelayFlightHour.getItemAt(DelayFlightHour.getSelectedIndex());
        String minutes = DelayFlightMinute.getItemAt(DelayFlightMinute.getSelectedIndex());
        
        Response response = FlightController.delayFlight(flightId, hours, minutes);
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        }else{
           
            DelayFlightID.setSelectedIndex(0);
            DelayFlightHour.setSelectedIndex(0);
            DelayFlightMinute.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
            
        }
    }//GEN-LAST:event_FlightDelayButtonActionPerformed

    private void RefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshButtonActionPerformed
        // TODO add your handling code here:
        String passengerId = addministrarionUser.getItemAt(addministrarionUser.getSelectedIndex());
        
        Response response = FlightControllerShowJtables.showAllMyFlights(passengerId);
        DefaultTableModel model = (DefaultTableModel) TablaMyFlights.getModel();
        model.setRowCount(0);
        
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            ArrayList<Object[]> rows = (ArrayList<Object[]>) response.getObject();
            for (Object[] row : rows) {
                model.addRow(row);
            }
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
        


    }//GEN-LAST:event_RefreshButtonActionPerformed

    private void RefreshShowAllPassengersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshShowAllPassengersActionPerformed
        Response response = PassengerControllerShowJtables.showAllPassengers();
        DefaultTableModel model = (DefaultTableModel) ShowPassengerTable.getModel();
        model.setRowCount(0);
        
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            ArrayList<Object[]> rows = (ArrayList<Object[]>) response.getObject();
            for (Object[] row : rows) {
                model.addRow(row);
            }
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_RefreshShowAllPassengersActionPerformed

    private void RefreshShowFlightsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshShowFlightsActionPerformed
        Response response = FlightControllerShowJtables.showAllFlights();
        DefaultTableModel model = (DefaultTableModel) ShowFlightsTable.getModel();
        model.setRowCount(0);
        
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            ArrayList<Object[]> rows = (ArrayList<Object[]>) response.getObject();
            for (Object[] row : rows) {
                model.addRow(row);
            }
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }//GEN-LAST:event_RefreshShowFlightsActionPerformed

    private void RefresShowPlanesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefresShowPlanesActionPerformed
        Response response = PlaneControllerShowJtables.showAllPlane();
        DefaultTableModel model = (DefaultTableModel) ShowPlanesTable.getModel();
        model.setRowCount(0);
        
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        }else{
            ArrayList<Object[]> rows = (ArrayList<Object[]>) response.getObject();
            for (Object[] row : rows) {
                model.addRow(row);
            }
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_RefresShowPlanesActionPerformed

    private void RefreshShowLocationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshShowLocationsActionPerformed
        Response response = LocationControllerShowJtables.showAllLocations();
        DefaultTableModel model = (DefaultTableModel) ShowLocationsTable.getModel();
        model.setRowCount(0);
        
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        }else{
            ArrayList<Object[]> rows = (ArrayList<Object[]>) response.getObject();
            for (Object[] row : rows) {
                model.addRow(row);
            }
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_RefreshShowLocationsActionPerformed

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitButtonActionPerformed
    
    
    
    private void addministrarionUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addministrarionUserActionPerformed
        try {
            String id = addministrarionUser.getSelectedItem().toString();
            if (! id.equals(addministrarionUser.getItemAt(0))) {
                UpdateInfoUserId.setText(id);
                AddToFlightUserId.setText(id);
            }
            else{
                UpdateInfoUserId.setText("");
                AddToFlightUserId.setText("");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_addministrarionUserActionPerformed
    
    private void UpdateInfoUserIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateInfoUserIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UpdateInfoUserIdActionPerformed

    private void AddToFlightUserIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddToFlightUserIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddToFlightUserIdActionPerformed

    private void LocationRegistration_AirportCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LocationRegistration_AirportCityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LocationRegistration_AirportCityActionPerformed

    private void Location_Registration_AirpotIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Location_Registration_AirpotIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Location_Registration_AirpotIDActionPerformed

    private void PassengerRegistrateLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PassengerRegistrateLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PassengerRegistrateLastNameActionPerformed

    private void PassengerRegistrateBirthdateYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PassengerRegistrateBirthdateYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PassengerRegistrateBirthdateYearActionPerformed

    private void PassagerRegistrationBirthdateDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PassagerRegistrationBirthdateDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PassagerRegistrationBirthdateDayActionPerformed

    private void DelayFlightHourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DelayFlightHourActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DelayFlightHourActionPerformed

    private void PassengerRegistrateIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PassengerRegistrateIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PassengerRegistrateIDActionPerformed

    /**
     * @param args the command line arguments
     */
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> AddToFightBox;
    private javax.swing.JButton AddToFlightButton;
    private javax.swing.JTextField AddToFlightUserId;
    private javax.swing.JTextField AirplaneRAirlineTextfield;
    private javax.swing.JTextField AirplaneRBrandTextfield;
    private javax.swing.JTextField AirplaneRIdTextfield;
    private javax.swing.JTextField AirplaneRMaxCapacityTextfield;
    private javax.swing.JTextField AirplaneRModelTextfield;
    private javax.swing.JButton CreateAirplaneButton;
    private javax.swing.JButton CreateFlightButton;
    private javax.swing.JButton Create_LocationRegistration;
    private javax.swing.JComboBox<String> DelayFlightHour;
    private javax.swing.JComboBox<String> DelayFlightID;
    private javax.swing.JComboBox<String> DelayFlightMinute;
    private javax.swing.JButton ExitButton;
    private javax.swing.JButton FlightDelayButton;
    private javax.swing.JTextField LocationRegistration_AirportCity;
    private javax.swing.JTextField LocationRegistration_AirportCountry;
    private javax.swing.JTextField LocationRegistration_AirportLatitude;
    private javax.swing.JTextField LocationRegistration_AirportLongitude;
    private javax.swing.JTextField LocationRegistration_AirportName;
    private javax.swing.JTextField Location_Registration_AirpotID;
    private javax.swing.JTabbedPane PaneShowAllLocations;
    private javax.swing.JComboBox<String> PassagerRegistrationBirthdateDay;
    private javax.swing.JTextField PassengerRegistrateBirthdateYear;
    private javax.swing.JTextField PassengerRegistrateCountry;
    private javax.swing.JTextField PassengerRegistrateCountryPhoneCode;
    private javax.swing.JTextField PassengerRegistrateFirstName;
    private javax.swing.JTextField PassengerRegistrateID;
    private javax.swing.JTextField PassengerRegistrateLastName;
    private javax.swing.JTextField PassengerRegistrateNumber;
    private javax.swing.JComboBox<String> PassengerRegistrationBirthdateMonth;
    private javax.swing.JButton RefresShowPlanes;
    private javax.swing.JButton RefreshButton;
    private javax.swing.JButton RefreshShowAllPassengers;
    private javax.swing.JButton RefreshShowFlights;
    private javax.swing.JButton RefreshShowLocations;
    private javax.swing.JButton RegisterPassenger;
    private javax.swing.JLabel RegistrationBirthdate;
    private javax.swing.JLabel RegistrationCountry;
    private javax.swing.JLabel RegistrationFirstName;
    private javax.swing.JLabel RegistrationID;
    private javax.swing.JLabel RegistrationLastName;
    private javax.swing.JLabel RegistrationPhone;
    private javax.swing.JTable ShowFlightsTable;
    private javax.swing.JTable ShowLocationsTable;
    private javax.swing.JTable ShowPassengerTable;
    private javax.swing.JTable ShowPlanesTable;
    private javax.swing.JTable TablaMyFlights;
    private javax.swing.JTextField UpdateInfoBirthdate;
    private javax.swing.JButton UpdateInfoButton;
    private javax.swing.JTextField UpdateInfoCountry;
    private javax.swing.JComboBox<String> UpdateInfoDay;
    private javax.swing.JTextField UpdateInfoFirstName;
    private javax.swing.JTextField UpdateInfoLastName;
    private javax.swing.JComboBox<String> UpdateInfoMonth;
    private javax.swing.JTextField UpdateInfoPhone;
    private javax.swing.JTextField UpdateInfoPhoneCode;
    private javax.swing.JTextField UpdateInfoUserId;
    private javax.swing.JComboBox<String> addministrarionUser;
    private javax.swing.JRadioButton administrator;
    private javax.swing.JComboBox<String> flightRegistrationArrivalHour;
    private javax.swing.JComboBox<String> flightRegistrationArrivalLocation;
    private javax.swing.JComboBox<String> flightRegistrationArrivalMinute;
    private javax.swing.JTextField flightRegistrationDepartureDate;
    private javax.swing.JComboBox<String> flightRegistrationDepartureDay;
    private javax.swing.JComboBox<String> flightRegistrationDepartureHour;
    private javax.swing.JComboBox<String> flightRegistrationDepartureLocation;
    private javax.swing.JComboBox<String> flightRegistrationDepartureMinute;
    private javax.swing.JComboBox<String> flightRegistrationDepartureMonth;
    private javax.swing.JTextField flightRegistrationId;
    private javax.swing.JComboBox<String> flightRegistrationPlane;
    private javax.swing.JComboBox<String> flightRegistrationScaleHour;
    private javax.swing.JComboBox<String> flightRegistrationScaleLocation;
    private javax.swing.JComboBox<String> flightRegistrationScaleMinute;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private airport.views.PanelRound panelRound1;
    private airport.views.PanelRound panelRound2;
    private airport.views.PanelRound panelRound3;
    private javax.swing.JRadioButton user;
    // End of variables declaration//GEN-END:variables
}
