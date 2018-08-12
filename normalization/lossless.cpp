#include <bits/stdc++.h>

using namespace std;

typedef multimap<set<char>,set<char> > multi;
typedef multimap<set<char>,set<char> >::iterator mitr;
typedef pair<set<char>,set<char> > makepair;


typedef set<char>::iterator sitr;
bool issubset(string s1,string s2);
void getindices(string temp,vector<int > &d);
void gettostring(set<char> s,string &temp);
void gettoset(string s,set<char> &t);



string attr;


int main()
{
	multi m;

	cin>>attr;// attributes
	int n;
	cin>>n;//no of relations
	string rel[n];
	for(int i=0;i<n;i++)
	{
		cout<<"relations"<<endl;
		cin>>rel[i];
	}
	int a[n][attr.length()];

	string kl;
	for(int i=0;i<n;i++)
	{
	for(int j=0;j<attr.length();j++)
			a[i][j]=0;
	}
	for(int i=0;i<n;i++)
	{
		for(int j=0;j<attr.length();j++)
		{
			kl+=attr[j];
			if(issubset(kl,rel[i]))
			{
				a[i][j]=1;
			}
			kl.clear();
		}
	}
	
	string lhs,rhs,temp,temp1;
	set<char> l,r;
	int p;
	cin>>p;//no of fds
	for(int i=0;i<p;i++)
	{
		cout<<"lhs: ";
		cin>>lhs;
		cout<<"rhs: ";
		cin>>rhs;
		gettoset(lhs,l);
		gettoset(rhs,r);

		m.insert(makepair (l,r));
		l.clear();
		r.clear();

	}
	int flag1,flag,no_of_changes;
	vector<int > d,d1;
	
		no_of_changes=0;
	for(mitr mit=m.begin();mit!=m.end();mit++)
	{
		temp.clear();
		temp1.clear();
		d.clear();
		d1.clear();
		gettostring(mit->first,temp);
		gettostring(mit->second,temp1);
		getindices(temp,d);
		cout<<d.size()<<endl;
		getindices(temp1,d1);
		for(int i=0;i<n;i++)
			for(int j=0;j<attr.length();j++)
			{
				flag=0;
				for(int k=0;k<d.size();k++)
				{
					cout<<attr[d[k]]<<endl;
					if(a[i][d[k]]!=1)
						flag=1;
				}
				if(flag==0)
				{
					for(int k=0;k<n;k++)
					{
						for(int l=0;l<n;l++)
						{
							if(l!=k)
							{
								flag1=0;
								for(int m=0;m<d.size();m++)
								{
									if(a[k][d[m]]!=a[l][d[m]])
										flag1=1;
								}
								if(flag1==0)
								{
									
									for(int m=0;m<d1.size();m++)
									{
										if(a[k][d1[m]]==0)
										{
											no_of_changes++;
										a[k][d1[m]]=a[l][d1[m]];
										}
									}
								}
							}
						}
					}
				}
			}
	}


for(int i=0;i<n;i++)
{
	for(int j=0;j<attr.length();j++)
		cout<<a[i][j]<<" ";
	cout<<endl;
}
int count=0;
for (int i = 0; i < n; ++i)
{
	count=0;
	for(int j=0;j<attr.length();j++)
	{
		if(a[i][j]==1)
			count++;
	}
	if(count==attr.length())
	{
		cout<<"lossless"<<endl;
		return 0;
	}
	
}
cout<<"lossy"<<endl;
return 0;
	
}

void gettoset(string s,set<char> &t)
{
	for(int i=0;i<s.length();i++)
		t.insert(s[i]);
}

void gettostring(set<char> s,string &temp)
{
	for(sitr sit=s.begin();sit!=s.end();sit++)
	{
		temp+=*sit;
	}
}

void getindices(string temp,vector<int > &d)
{
	cout<<temp<<"```";
	cout<<attr<<endl;
	for(int i=0;i<temp.length();i++)
	{
		for(int j=0;j<attr.length();j++)
		{
			if(temp[i]==attr[j])
				d.push_back(j);
		}
	}
	cout<<"get"<<d.size()<<endl;
}
bool issubset(string s1,string s2)
{
	int flag=0;
	
	if(s1.size()>s2.size())
		return false;
	else
	{
		for(int i=0;i<s1.length();i++)
		{
			flag=0;
			for(int j=0;j<s2.length();j++)
			{
				if(s1[i]==s2[j])
					flag=1;
			}
			if(flag==0)
				return false;
		}
		return true;
	}
}