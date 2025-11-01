package helloWorldClient;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import HelloWorldApp.Hello;
import HelloWorldApp.HelloHelper;

public class Main {
	static Hello hello;
	public static void main(String[] args) throws InvalidName, NotFound, CannotProceed, org.omg.CosNaming.NamingContextPackage.InvalidName {
		ORB orb =ORB.init(args,null);
		org.omg.CORBA.Object objRef=orb.resolve_initial_references("name service");
		NamingContextExt ncRef=  NamingContextExtHelper.narrow(objRef);
		
		String name="Hello";
		hello=HelloHelper.narrow(ncRef.resolve_str(name));
		System.out.println("Obtained a handle on server object: "+hello);
		System.out.println(hello.HelloMessage());
		
	}

}
