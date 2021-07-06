什么是swing
swing是java的一套轻量级的gui组件库，因为没有调用本地系统内在的方法，所以是跨平台的。

Swing的包和类结构
javax.swing 几乎所有的swing组件都在这个包内。
Swing 控件分为容器和组件。组件无法单独存在，必须被包含在容器中；所有的容器继承自container，所有的组件继承jComponent，
jComponent也是继承自container。
顶级容器有：jFrame, jDialog，jApplet，组件JComponent也有一部分充当了中间组件，例如JPanel

JFrame的使用，
setTitle设置标题
setSize设置大小
setVisiable设置可见性。

布局管理器，方便容器对组件进行同一管理。
setLayout(new BorderLayout()) 设置管理器的api
下面介绍各种布局管理器
BorderLayout 边框管理器，将布局分为五个部分（东南西北中，对应右下左上中 和地图的方向是一致的）
FlowLayout  流式布局
CardLayout  卡片布局，组件共享一个显示空间，一次显示一个，用于切换。 cardLayout.show(jcontainer, "panel2"); 
CardLauyout必须在jContainer添加组件前设置，后面的切换操作才会生效。
GridLayout  网格布局。
BoxLayout  盒布局通常盒box一起使用，Box.createVerticalBox()/createHorizontalBox()创建垂直和水平的组件。

JLabel,标签是一种可以包**含文本和图片**的**非交互**组件
JButton, 按钮
JTextField  当行文本框。
JTextArea   文本域
JCheckBox   复选框组件
JRadioButton  单选框，必须在ButtonGroup中才有意义
JComboBox   下拉列表，可以监听item事件
JList       列表组件

事件监听，
点击事件，addActionListener
焦点事件，addFocusListener (FocusAdapter)
列表点击事件， addItemListener, addListSelectionListener

JSlider  滑块组件
JProgressBar  进度条组件

Timer    计时器组件，可以在特定的时间间隔后发送ActionEvent事件。
JFileChooser()  文件选择器，为用户操作系统文件提供了桥梁。可以设置选择的模式file/direction/save/
JColorChooser()  颜色选择，

JOptionPanel :对话框组件，可以创建四种标准的对话框，确认，消息，输入，选项。
JDialog : JOptionPanel的扩展类，常用于自定义对话框。
JTable  : 表格
JTree   : 树结构，例如目录

JTabbedPanel   选项卡组件。





