/*
 * In this case we have a SUT and DOC. DOC inject data to the SUT
 * To learn in this exercise:
 * a) Parameterized test with user defined class parameter.
 * b) Using Test stub to inject data to SUT when toString() method in SUT is called.
 * 
 *
 */
package Classes;

import Interfaces.Autor;
import java.util.stream.Stream;
import static org.fest.assertions.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author mauriciobedoya
 */
public class BookTest {
    private Book book;   // SUT (SYSTEM UNDER TEST is book class)
    @Mock
    private Autor autor; // Non static DOC (DEPENDED ON COMPONENT is autor class / interface)
    @Mock
    private static Autor autor1, autor2;  //Static DOC (DEPENDED ON COMPONENT is autor class / interface)
    private static class AutorArgumentProvider implements ArgumentsProvider{
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext ec) throws Exception {
            return Stream.of(autor1,autor2).map(Arguments::of);
        }
    }
    
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        book = new Book("XUnit Test Patterns", autor, 35.5);
    }
    
    @Test
    public void bookHasAnAutor(){
        assertThat(book.getAutor()).isNotNull();
    }
    
    @ParameterizedTest
    @ValueSource(doubles = {20.0, 25.5, 35.5})
    public void bookPriceChange(double newPrice){
        book.setPrice(newPrice);
        assertThat(book.getPrice()).isEqualTo(newPrice);
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"Name1", "Name2", "Name3"})
    public void bookNameChange(String newName){
        book.setName(newName);
        assertThat(book.getName()).isEqualTo(newName);
    }
    
    @ParameterizedTest
    @ArgumentsSource(AutorArgumentProvider.class)
    public void bookAutorChange(Autor autor){
        book.setAutor(autor);
        assertThat(book.getAutor()).isEqualTo(autor);
    }
    
    @Test
    public void bookToString(){
        when(autor.getName()).thenReturn("Gerard M");
        when(autor.getEmail()).thenReturn("m_gerard@hotmail.com");
        when(autor.getGender()).thenReturn('M');
        String expected = book.getName() + " by " + book.getAutor().getName() 
                + "(" + book.getAutor().getGender() + ") at " + book.getAutor().getEmail();
        assertThat(book.toString()).isEqualTo(expected);
    }
}
