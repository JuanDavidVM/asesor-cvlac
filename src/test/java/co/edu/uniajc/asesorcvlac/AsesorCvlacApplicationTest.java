package co.edu.uniajc.asesorcvlac;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/** Prueba de humo: verifica que el contexto de Spring arranca correctamente. */
@SpringBootTest
class AsesorCvlacApplicationTest {

  @Autowired private ApplicationContext context;

  @Test
  @DisplayName("El contexto de la aplicación carga sin errores")
  void contextLoads() {
    assertNotNull(context);
  }
}
