package hu.bme.mit.spaceship;



import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class GT4500Test {

  private GT4500 ship;
  TorpedoStore mock1;
  TorpedoStore mock2;

  @BeforeEach
  public void init(){
    mock1 = mock(TorpedoStore.class);
    mock2 = mock(TorpedoStore.class);
    this.ship = new GT4500(mock1,mock2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mock1.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    verify(mock1, times(1)).fire(1);

    assertEquals(true, result);

  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mock1.fire(1)).thenReturn(true);
    when(mock2.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    // Assert
    verify(mock1, times(1)).fire(1);
    verify(mock2, times(1)).fire(1);

    assertEquals(true, result);
  }

  @Test
  public void fireTorpedoAll_One_Succes(){
    // Arrange
    when(mock1.fire(1)).thenReturn(true);
    when(mock2.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    // Assert
    verify(mock1, times(1)).fire(1);
    verify(mock2, times(1)).fire(1);

    assertEquals(false, result);

  }

  @Test
  public void fireTorpedoAll_All_Fail(){
    // Arrange
    when(mock1.fire(1)).thenReturn(false);
    when(mock2.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    // Assert
    verify(mock1, times(1)).fire(1);
    verify(mock2, times(1)).fire(1);

    assertEquals(false, result);

  }

  @Test
  public void fireTorpedoWasPrimary_Second_Succes(){
    // Arrange
    ship.setWasPrimary(true);
    when(mock1.fire(1)).thenReturn(false);
    when(mock2.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    verify(mock1, times(0)).fire(1);
    verify(mock2, times(1)).fire(1);

    assertEquals(true, result);

  }
  @Test
  public void fireTorpedoWasPrimary_Second_Fail(){
    // Arrange
    ship.setWasPrimary(true);
    when(mock1.fire(1)).thenReturn(false);
    when(mock2.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    verify(mock1, times(0)).fire(1);
    verify(mock2, times(1)).fire(1);

    assertEquals(false, result);

  }
  @Test
  public void fireTorpedoWasNotPrimary_First_Success(){
    // Arrange
    when(mock1.fire(1)).thenReturn(true);
    when(mock2.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    verify(mock1, times(1)).fire(1);
    verify(mock2, times(0)).fire(1);

    assertEquals(true, result);

  }
  @Test
  public void fireTorpedoWasNotPrimary_First_Fail(){
    // Arrange
    when(mock1.fire(1)).thenReturn(false);
    when(mock2.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    verify(mock1, times(1)).fire(1);
    verify(mock2, times(0)).fire(1);

    assertEquals(false, result);

  }

}