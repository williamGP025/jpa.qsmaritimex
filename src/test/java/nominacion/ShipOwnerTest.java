/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nominacion;

import javax.persistence.Persistence;
import org.junit.jupiter.api.Test;

/**
 *
 * @author WilliamGP025
 */
public class ShipOwnerTest {
   
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hello() {
         Persistence.generateSchema("qsmaritimex_jpa", null);
     }
}