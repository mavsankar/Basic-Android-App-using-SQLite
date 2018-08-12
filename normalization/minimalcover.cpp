#include <bits/stdc++.h>
using namespace std;

typedef multimap<set<char>,set<char> >::iterator mitr;
typedef set<char>::iterator sitr;
typedef pair<set<char>,set<char> > makepair;
typedef multimap<set<char>,set<char> > multi;

void printfd(multi &m);
void gettoset(string s,set<char> &t);
int rhsdecomp(multi &m);
void minimalcover(multi &m);
int lhsdecomp(multi &m);
bool issubset(set<char> s1,set<char> s2);
void closure(set<char> s1,multi &m,set<char> &s);
int removeredundancy(multi &m);

set<char> rel;
string relation;
int main()
{
	multimap<set<char>,set<char> > m;
	int n;
	cout<<"Enter no of attributes"<<endl;
	cin>>n;
	string l,r;
	set<char> lhs,rhs;
	cout<<"Enter the attributes"<<endl;
	cin>>relation;
	for(int i=0;i<relation.length();i++)
	{
		rel.insert(relation[i]);
	}
	cout<<"Enter no of fds"<<endl;
	int p;
	cin>>p;
	for(int i=0;i<p;i++)
	{
		cout<<"lhs: ";
		cin>>l;
		cout<<"rhs: ";
		cin>>r;
		gettoset(l,lhs);
		gettoset(r,rhs);
		m.insert(makepair (lhs,rhs));
		lhs.clear();
		rhs.clear();
	}
	printfd(m);
	minimalcover(m);
	printfd(m);

}

void minimalcover(multi &m)
{
	int no_of_changes;
	do{
		no_of_changes=0;
		no_of_changes+=rhsdecomp(m);
		no_of_changes+=lhsdecomp(m);
		no_of_changes+=removeredundancy(m);
	}while(!no_of_changes);
}

int rhsdecomp(multi &m)
{
	int no_of_changes=0;
	set<char> temp;
	for(mitr mit=m.begin();mit!=m.end();)
	{
		if((mit->second).size()>1)
		{
			for(sitr sit=(mit->second).begin();sit!=(mit->second).end();sit++)
			{
				no_of_changes++;
				temp.insert(*sit);
				m.insert(makepair ((mit->first),temp));
				temp.clear();
			}
			m.erase(mit++);
		}
		else
			mit++;
	}
	return no_of_changes;
}

int lhsdecomp(multi &m)
{
	int no_of_changes=0,flag;
	multi m2;
	for(mitr mit=m.begin();mit!=m.end();mit++)
	{
		flag=0;
		for(mitr mmit=m.begin();mmit!=m.end();mmit++)
			{
				if(issubset(mmit->first,mit->first) && issubset(mmit->second,mit->first) && mmit->first!=mit->first && mmit->second!=mit->first)
				{
					flag=1;
					no_of_changes++;
					m2.insert(makepair (mmit->first,mit->second));
					
				}
					
			}	
			if(flag==0)
			{
				m2.insert(makepair (mit->first,mit->second));
			}
	}
	m=m2;
	return no_of_changes;
}

int removeredundancy(multi &m)
{
	int no_of_changes=0;
	set<char> temp1,temp2;
	multi m2;
	m2=m;
	int flag=0,i=0;
	for(mitr mit=m2.begin();mit!=m2.end();)
	{
		m2.erase(mit++);
		
		for(mitr mmit=m.begin();mmit!=m.end();mmit++)
		{
			closure(mmit->first,m2,temp1);
			closure(mmit->first,m,temp2);
			if(temp1!=temp2)
			{
				flag=1;
			}
			temp1.clear();
			temp2.clear();
		}
		if(flag==0)
		{
			no_of_changes++;
			m=m2;
		}
		else
		{
			m2=m;
			mit=m2.begin();
			for(int j=0;j<=i;j++)
				mit++;
			i++;
		}
		flag=0;

	}
	return no_of_changes;
}

void closure(set<char> s1,multi &m,set<char> &s)
{
	set<char> olds;
	
	while(olds!=s1)
	{
		olds=s1;
		for(mitr mit=m.begin();mit!=m.end();mit++)
		{
			if(issubset(mit->first,olds))
			{
				for(sitr sit=(mit->first).begin();sit!=(mit->first).end();sit++)
				{
					s1.insert(*sit);
				}
				for(sitr sit=(mit->second).begin();sit!=(mit->second).end();sit++)
				{
					s1.insert(*sit);
				}
			}
		}
	}
	s=s1;
	/*for(sitr sit=s.begin();sit!=s.end();sit++)
		cout<<*sit<<" ";
	cout<<endl;*/

}
bool issubset(set<char> s1,set<char> s2)
{
	
	int flag=0;
	
	if(s1.size()>s2.size())
		return false;
	else
	{
		for(sitr sit=s1.begin();sit!=s1.end();sit++)
		{
			flag=0;
			for(sitr ssit=s2.begin();ssit!=s2.end();ssit++)
			{
				if(*sit==*ssit)
					flag=1;
			}
			if(flag==0)
				return false;
		}
		return true;
	}
}

void gettoset(string s,set<char> &t)
{
	for(int i=0;i<s.length();i++)
		t.insert(s[i]);
}
void printfd(multi &m)
{
	for(mitr mit=m.begin();mit!=m.end();mit++)
	{
		for(sitr sit=(mit->first).begin();sit!=(mit->first).end();sit++)
		{
			cout<<*sit;
		}
			cout<<" --> ";
		for(sitr sit=(mit->second).begin();sit!=(mit->second).end();sit++)
		{
			cout<<*sit;
		}	
		cout<<" ";
	}
	cout<<endl;
}