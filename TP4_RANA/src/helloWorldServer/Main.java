package helloWorldServer;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import HelloWorldApp.Hello;
import HelloWorldApp.HelloHelper;

public class Main {
public static void main(String[] args) throws InvalidName, AdapterInactive, ServantNotActive, WrongPolicy, org.omg.CosNaming.NamingContextPackage.InvalidName, NotFound, CannotProceed {
	ORB orb=ORB.init(args,null);
	POA rootpoa =POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
	rootpoa.the_POAManager().activate();
	HelloServant helloservant =new HelloServant();
	helloservant.setOrb(orb);
	org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloservant);
	Hello href=HelloHelper.narrow(ref);
	Object objRef =orb.resolve_initial_references("nameService");
	NamingContextExt ncRef=NamingContextExtHelper.narrow(objRef);
	String name ="hello";
	NameComponent path[] =ncRef.to_name(name);
	ncRef.rebind(path, href);
	System.out.println("HelloWorldServer ready and waiting...");
	for(;;) {
		orb.run();
	}
	

}
}
