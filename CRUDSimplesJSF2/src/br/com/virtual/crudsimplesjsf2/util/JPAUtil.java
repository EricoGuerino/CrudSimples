package br.com.virtual.crudsimplesjsf2.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil 
{
	private static JPAUtil jpaUtil;
	final EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRUDSimplesJSF2PU");
	EntityManager em;
	
	private JPAUtil(){}
	
	public static JPAUtil getInstance() 
    {  
        if (jpaUtil == null) 
        {  
            jpaUtil = new JPAUtil();  
        }  
        return jpaUtil;  
    }  
  
    public EntityManager getEntityManager() 
    {  
        return emf.createEntityManager();  
    }
	
	public void fecharEntityManager()
	{
		em.close();
	}
}
