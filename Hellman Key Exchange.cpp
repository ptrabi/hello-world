#include<iostream>
#include <math.h>  
#include <memory>
using namespace std;

bool prime(int q){
		  for(int i = 2; i <= q / 2; ++i)
  {
      if(q % i == 0)
      {
      
		return 0;
      }
  }
 return 1;
}
	
bool cariprimitiv(int q,int po) 
{ 
	int i,j;
	int sim[q-1];
    for(i=1, j=0; i<q;i++,j++){
    	sim[j]=(int)pow(po,i)%q;
    //	cout<<po<<" ^ "<<i <<" % "<<q<<"="<<sim[j]<<endl;
	}
	 
   
    for(i = 0;i < q-1; i++) { 
        for(int j = i; j < 6; j++) { 
            if(j != i) { 
                if(sim[i] == sim[j]) {
                   return false;
                }
            }      
        } 
    } 
return true;
} 

int main(){
 	int q,xa,xb,a,ya,yb,k,i;
	 bool isPrime = true;
	cout <<"Input q = ";
	cin>>q;
	if(!prime(q)){
		cout << "This is not a prime number";
	return 0;
	};
	
	for(i=1;i<q;i++){
		if(cariprimitiv(q,i)){
		a=i;
		break;
		}
	}
	


	cout<<"Input xa ";
	cin>>xa;
	cout<<"Input xb ";
	cin>>xb;


ya=(int)pow(a,xa)%q;
yb=(int)pow(a,xb)%q;
k=(int)pow(ya,xb)%q;
cout<<endl<<endl<<"Maka hasil yang didapatkan adalah"<<endl<<"a =  "<< a<<endl;
cout<<"ya = "<< ya<<endl;
cout<<"yb = "<< yb<<endl;
 cout<<"k = "<<k;


}
