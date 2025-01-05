import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

class Statistics extends JFrame implements ActionListener{
	JLabel number,number1,elements,Ans;
	JTextField tnumber,ttnumber;
	JTextField[] telements;
	JTextArea tAns;
	JButton Enter,Calculate,Reset,EXIT;
	JScrollPane sAns;
	int noOfFields=0,Index=0;

	public Statistics(){
		setTitle("Statistics Calculator(Ungrouped Data)");
		setBounds(100,100,700,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		setLayout(null);

		number=new JLabel("Enter the number of elements you want in a dataset :");
		number.setFont(new Font("Century Schoolbook",Font.PLAIN,15));
		number.setBounds(20,20,400,20);
		add(number);

		tnumber=new JTextField();
		tnumber.setFont(new Font("Century Schoolbook",Font.PLAIN,15));
		tnumber.setBounds(425,20,100,20);
		add(tnumber);

		Enter=new JButton("Enter");
		Enter.setFont(new Font("Century Schoolbook",Font.PLAIN,15));
		Enter.setBounds(525,20,100,20);
		Enter.addActionListener(this);
		add(Enter);

		elements=new JLabel("Enter your elements here :");
		elements.setFont(new Font("Century Schoolbook",Font.PLAIN,15));
		elements.setBounds(20,50,200,20);
		add(elements);

		number1=new JLabel("Enter the no.of raw moments and central moments you want(n>=4) : ");
		number1.setFont(new Font("Century Schoolbook",Font.PLAIN,15));
		number1.setBounds(20,150,500,20);
		add(number1);

		ttnumber=new JTextField();
		ttnumber.setFont(new Font("Century Schoolbook",Font.PLAIN,15));
		ttnumber.setBounds(525,150,50,20);
		add(ttnumber);

		Calculate=new JButton("Calculate");
		Calculate.setFont(new Font("Century Schoolbook",Font.PLAIN,15));
		Calculate.setBounds(20,180,120,20);
		Calculate.addActionListener(this);
		add(Calculate);

		Reset=new JButton("Reset");
		Reset.setFont(new Font("Century Schoolbook",Font.PLAIN,15));
		Reset.setBounds(170,180,120,20);
		Reset.addActionListener(this);
		add(Reset);

		EXIT=new JButton("EXIT");
		EXIT.setFont(new Font("Century Schoolbook",Font.PLAIN,15));
		EXIT.setBounds(320,180,70,20);
		EXIT.addActionListener(this);
		add(EXIT);

		Ans=new JLabel("Answer :");
		Ans.setFont(new Font("Century Schoolbook",Font.PLAIN,15));
		Ans.setBounds(20,210,100,20);
		add(Ans);

		tAns=new JTextArea();
		tAns.setFont(new Font("Century Schoolbook",Font.PLAIN,15));
		tAns.setBounds(125,210,500,400);
		add(tAns);

		sAns=new JScrollPane(tAns);
		sAns.setBounds(125,210,500,400);
		sAns.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(sAns);

		setVisible(true);
	}
	public void createTextFields(){
		if (noOfFields > 0) {
            telements = new JTextField[noOfFields];
    		for (int i = 0; i < noOfFields; i++) {
          		telements[i] = new JTextField();
				telements[i].setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
      			telements[i].setBounds(20+i*30,90,30,20);
				telements[i].setVisible(true);
                telements[i].setEditable(true); 
        		telements[i].addKeyListener(new KeyAdapter() {
				    public void keyPressed(KeyEvent e) {
    					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
    						if (Index < noOfFields - 1) {
          						Index++;
            					telements[Index].requestFocus();
    						} else {}
						}
    				}
				});
        		add(telements[i]);
        	}
    		revalidate();
	    }
	}
	public void actionPerformed(ActionEvent e){
		int n=Integer.parseInt(tnumber.getText());
		if(e.getSource()==Calculate){
			StringBuilder s=new StringBuilder();
			Calc(s);
			for(int i=0;i<n;i++){
				telements[i].setEditable(false);
			}
			tAns.setVisible(true);
			tAns.setText(s.toString());
			tAns.setEditable(false);
		}
		else if(e.getSource()==Reset){
			String Def="";
			tAns.setText(Def);
			for(int i=0;i<n;i++){
				telements[i].setVisible(false);
			}
			tnumber.setText(Def);
			ttnumber.setText(Def);
			noOfFields = 0;
		}
		else if(e.getSource()==EXIT){
    		System.exit(0);
		}
		else if(e.getSource()==Enter){
			try{
				noOfFields=Integer.parseInt(tnumber.getText());
				createTextFields();
				for(int i=0;i<noOfFields;i++){
					telements[i].setVisible(true);
				}
			}
			catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(null,"Please enter a valid number.");
			}
		}
	}

	public void Calc(StringBuilder s){
		int n = Integer.parseInt(tnumber.getText());
		double arr[]=new double[n];
        double Md[]=new double[n];
        
        for(int i=0;i<arr.length;i++){
			arr[i] = Double.parseDouble(telements[i].getText());
    	}
        
    	s.append("\nNow, first arranging the data in an ascending order as :\n");
    	for(int i=1;i<arr.length;i++){
        	double c=arr[i];
        	int j=i-1;
        	while(j>=0 && c<arr[j]){
            	arr[j+1]=arr[j];
            	j--;
            }
        	arr[j+1]=c;
    	}
	    for(int i=0;i<arr.length;i++){
            s.append(String.format("%.0f ", arr[i]));
        }

        int nn = Integer.parseInt(ttnumber.getText());
        double raw[]=new double[nn];
    	double cen[]=new double[nn];
        
        s.append("\n\n*****TYPES OF MEAN*****\n");
        double sum=0.0,Arm=0.0,Geo=1.0,Har=0.0;
        for (int i=0;i<raw.length;i++){
       		sum = 0;
        	for (int j=0; j < arr.length; j++) {
                sum += Math.pow(arr[j], i+1);
            	raw[i]=sum;
        	}
        	raw[i] /= arr.length;
    	}
        for(int i=0;i<arr.length;i++){
        	sum=0;
        	sum+=arr[i];
        	Geo=Geo*arr[i];
			Har=Har+(1/arr[i]);
        }
    	Arm=raw[0];
    	Geo=Math.pow(Geo,1.0/n);
        Har=(1.0*n)/Har;
    	s.append(String.format("Arithmetic mean = %.4f.\nGeometric mean = %.4f.\nHarmonic mean %.4f.\n",Arm,Geo,Har));
        
        double median,Mad=0,large,small,range,IQR,SIR,m,q1,q2,q3;
        if(arr.length%2!=0){
        	median=arr[n/2];
        	q2=median;
    	}
    	else{
            median=(arr[(n/2)-1] + arr[n/2])/2;
        	q2=median;
    	}
        for(int i=0;i<Md.length;i++){
        	sum=0;
        	Md[i]=sum+(arr[i]-raw[0]);
    	}
        for (int i=0;i<cen.length;i++){
        	sum = 0;
        	for (int j=0; j < Md.length; j++) {
                sum += Math.pow(Md[j], i+1);
            	cen[i]=sum;
        	}
        	cen[i] /= Md.length;
    	}
        
        Mad=0;
    	for(int i=0;i<Md.length;i++){
            Md[i]=Math.abs(Md[i]);
        	Mad+=Md[i];
    	}
    	Mad=Mad/Md.length;
        large=arr[0];small=arr[0];
        for(int i=0;i<arr.length;i++){
            if(arr[i]>large)
            	large=arr[i];
        	if(arr[i]<small)
            	small=arr[i];
    	}
    	range=large-small;
        
        m=arr.length/4;q1=0;q3=0;
    	if((4*m+3)/arr.length==1){
        	q1=arr[n/4];
            q3=arr[3*n/4];
        }
    	else if((4*m+2)/arr.length==1){
        	q1=arr[n/4];
        	q3=arr[3*n/4];
    	}
    	else if((4*m+1)/arr.length==1){
            q1=(arr[(n/4)-1]+arr[n/4])/2;
        	q3=(arr[3*n/4]+arr[(3*n/4)+1])/2;
    	}
    	else if((4*m+0)/arr.length==1){
            q1=(arr[(n/4)-1]+arr[n/4])/2;
        	q3=(arr[(3*n/4)-1]+arr[3*n/4])/2;
    	}
        IQR=q3-q1;
        SIR=1.0f*(IQR/2);
        s.append(String.format("\nMedian = %.0f\n",median));
        Map<Double, Integer> frequencyMap = new HashMap<>();
    	for (double value : arr) {
        	frequencyMap.put(value, frequencyMap.getOrDefault(value, 0) + 1);
    	}
        int maxFrequency = Collections.max(frequencyMap.values());
        List<Double> modes = new ArrayList<>();
        for (Map.Entry<Double, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                modes.add(entry.getKey());
            }
        }
    	if (modes.size() == arr.length) {
        	s.append(String.format("Mode = "+modes.get(0)));
        }
		else{
            s.append(String.format("Mode = "+modes));
        }
        s.append(String.format("\nMean Absolute Deviation = %.4f\nRange = %.0f\n\n***QUARTILE RANGES***\nQ1 = %.4f, Q2 = %.4f, Q3 = %.4f\nInter-Quartile Range = %.4f\nSemi-Interquartile Range = %.4f\n",Mad,range,q1,q2,q3,IQR,SIR));
		s.append("\n***RAW MOMENTS***\n");
        for (int i = 0; i <raw.length; i++) {
        	s.append(String.format("u%d' = %.4f\n", i+1, raw[i]));
    	}
        s.append("\n***CENTRAL MOMENTS***\n");
        for (int i = 0; i <cen.length; i++) {
        	s.append(String.format("u%d = %.4f\n", i+1, cen[i]));
    	}
    	s.append("\n");
        
        double va,SD,CV,b1,b2,y1,y2;
    	va=cen[1];
    	SD=1.0*(Math.pow(cen[1],0.5));
        CV=1.0*(SD/raw[0])*100;
    	b1=1.0*(Math.pow(cen[2],2))/(Math.pow(cen[1],3));
    	b2=1.0*(Math.pow(cen[3],1))/(Math.pow(cen[1],2));
        y1=Math.pow(b1,0.5);
        y2=b2-3.0;
        s.append(String.format("Variance = %.4f\nStandard Deviation = %.4f\nCoefficient of Variance = %.2f\n\n***SKEWNESS***\nB1 = %.4f\n",va,SD,CV,b1));
        if (cen[2]>=0){
            y1=y1*(1.0);
            s.append(String.format("y1 = %.3f",y1));
        }else{
        	y1=y1*(-1.0);
        	s.append(String.format("y1 = %.3f",y1));
        }
        if(y1>0)
        	s.append("\nThe curve is Right-Skewed.");
        else if(y1<0)
        	s.append("\nThe curve is Left-Skewed.");
    	else if(y1==0)
            s.append("\nThe curve is Symmetric.");
        else{}
        s.append(String.format("\n\n***KURTOSIS***\nB2 = %.4f\ny2 = %.3f",b2,y2));
        if(y2>0)
       		s.append("\nThe curve is Leptokurtic.");
    	else if(y2<0)
            s.append("\nThe curve is Platykurtic.");
        else if(y2==0)
            s.append("\nThe curve is Mesokurtic.");
        else{}
    }
}

class SwingCalculatorForUngroupedData{
	public static void main(String args[])throws Exception{
		Statistics s=new Statistics();
	}
}