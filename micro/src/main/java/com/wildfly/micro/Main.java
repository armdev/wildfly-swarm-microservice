package com.wildfly.micro;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.transactions.TransactionsFraction;

public class Main {

    public static void main(String[] args) throws Exception {
        Container container = new Container();
        /*
         * Use specific TransactionFraction even though it doesn't do
	 * any more than the default one - for now.
         */

        container.fraction(TransactionsFraction.createDefaultFraction());

        // Start the container
        JAXRSArchive appDeployment = ShrinkWrap.create(JAXRSArchive.class);
        appDeployment.addResource(MyResource.class);
        appDeployment.addAllDependencies();
        appDeployment.addPackage("org.wildfly.micro");

        container.start();
        container.deploy(appDeployment);
    }
}
