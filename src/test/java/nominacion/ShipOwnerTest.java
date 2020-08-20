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

    @Test
    public void generateSchema() {
        Persistence.generateSchema("qsmaritimex_jpa", null);
    }
}