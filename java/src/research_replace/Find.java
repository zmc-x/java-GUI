package research_replace;

public class Find {
	public String sen;//ԭ�ַ���
	public String find;//��Ҫ���ҵ��ַ���
	public int []memory=new int[10000];
	public int me_cnt;
	public int flag=0;
	public Find(String sen,String find) {
		this.sen=sen;
		this.find=find;
	}
	//kmp�㷨
	public void kmp(String sen,String find) {
		int len=sen.length();
		int len1=find.length();
		char []major=sen.toCharArray();
		char []minor=find.toCharArray();
		if(len1>len) {
			flag=1;//û�еĻ�ֱ�Ӹ�ֵΪ1
		}
		else {
			int []next=new int[100];
			next[0]=-1;//һ��λ��ʧ��
			next[1]=0;//2��λ��ʧ��
			int i,j,k;
			i=j=0;
			k=1;
			int cnt=1;//�ж�ѭ������
			me_cnt=0;
			for(i=2;i<len1;i++) {
				while(k<len1&&j<len1) {
					if(minor[j]!=minor[k]) {
						cnt++;//����
						k=cnt;
						j=0;
					}
					else {
						k++;
						j++;
					}
				}
				next[i]=j;
			}
			i=j=0;
			int count=0;
			while(i<len) {//����ƥ��
				if(major[i]!=minor[j]) {
					j=next[j];//����next�����������滻
					if(j==-1) {
						i++;
						j++;
					}
				}
				else {
					i++;
					j++;
				}
				if(j>=len1) {
					count++;
					int start=i-len1+1;
					memory[me_cnt]=start;
					memory[me_cnt+1]=i;
					me_cnt=me_cnt+2;
					j=0;
				}
			}
			if(count==0) flag=1;//ͬ��
		}
	}
}
