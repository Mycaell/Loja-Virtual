package testes;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteUsuario {

	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\oxcol\\Downloads\\lojaDAC\\lojaDAC\\lojaDAC\\driver\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testaConsulta() throws InterruptedException {
		driver.get("http://localhost:8080/lojaDAC/usuarios.xhtml");
		Thread.sleep(2000);
		
//		xpath
		//*[@id="layout-center"]/div/main
		
		
//		WebElement element = driver.findElement(By.id("grid-users"));
//		System.out.println("element: "+element.getText());
//		System.out.println("\n------------\n");
//		
//		List<WebElement> usuarios = element.findElements(By.id("panel-user"));
//		System.out.println("elements: "+usuarios.get(1).getText());	
//	
//		
//		WebElement element2 = driver.findElement(By.id("form-add-user:messages"));
//		System.out.println("element: "+element2.getText());
//		System.out.println("\n------------\n");
//		Thread.sleep(2000);
	}
	
	
	@Test
	public void testaAdicaoInvalida() throws InterruptedException {

		driver.get("http://localhost:8080/lojaDAC/usuarios.xhtml");
		Thread.sleep(2000);

		WebElement botaoAddUsuario = driver.findElement(By.id("btAddUser"));
		botaoAddUsuario.click();
		Thread.sleep(2000);

		
		WebElement tituloDialog = driver.findElement(By.id("idAddUserDialog_title"));
		assertTrue(tituloDialog.equals("Novo usuário"));
		Thread.sleep(2000);

		
		
		
		WebElement campoNome = driver.findElement(By.id("form-add-user:nome"));
		campoNome.sendKeys("nome selenium");
		Thread.sleep(2000);

		WebElement campoSobrenome = driver.findElement(By.id("form-add-user:sobrenome"));
		campoSobrenome.sendKeys("sobrenome selenium");
		Thread.sleep(2000);

//		WebElement campoCpf = driver.findElement(By.id("form-add-user:cpf"));
//		campoCpf.clear();
//		campoCpf.sendKeys("11111111111");
//		Thread.sleep(2000);

		WebElement campoEmail = driver.findElement(By.id("form-add-user:email"));
		campoEmail.sendKeys("email@selenium");
		Thread.sleep(2000);

//		WebElement campoTelefone = driver.findElement(By.id("form-add-user:telefone"));
//		campoTelefone.sendKeys("(11) 1111-1111");
//		Thread.sleep(2000);

		WebElement campoFoto = driver.findElement(By.id("form-add-user:foto"));
		campoFoto.sendKeys("foto selenium");
		Thread.sleep(2000);

		WebElement campoSenha = driver.findElement(By.id("form-add-user:senha"));
		campoSenha.sendKeys("senha selenium");
		Thread.sleep(2000);

//		WebElement campoCep = driver.findElement(By.id("form-add-user:cep"));
//		campoCep.sendKeys("11111-111");
//		Thread.sleep(2000);

		WebElement campoCidade = driver.findElement(By.id("form-add-user:cidade"));
		campoCidade.sendKeys("cidade selenium");
		Thread.sleep(2000);

		WebElement campoBairro = driver.findElement(By.id("form-add-user:bairro"));
		campoBairro.sendKeys("bairro selenium");
		Thread.sleep(2000);

		WebElement campoRua = driver.findElement(By.id("form-add-user:rua"));
		campoRua.sendKeys("rua selenium");
		Thread.sleep(2000);

		WebElement campoNumero = driver.findElement(By.id("form-add-user:numero"));
		campoNumero.sendKeys("1111111");
		Thread.sleep(2000);

		WebElement campoComplemento = driver.findElement(By.id("form-add-user:complemento"));
		campoComplemento.sendKeys("complemento selenium");
		Thread.sleep(2000);

		WebElement botaoSalvar = driver.findElement(By.id("form-add-user:salvar"));
		botaoSalvar.click();
		Thread.sleep(2000);

		WebElement mensagem = driver.findElement(By.id("form-add-user:messages_container"));
		System.out.println("Mensagens: "+mensagem.getText());
		System.out.println("\n------------\n");
		Thread.sleep(2000);
		
		
		assertTrue(mensagem.getText().contains("Error"));
		
	}

	
	
//	@Test
//	public void testaEdicao() throws InterruptedException {
//		
//		driver.get("http://localhost:8080/lojaDAC/usuarios.xhtml");
//		Thread.sleep(2000);
//		
//		WebElement element = driver.findElement(By.id("grid-users:panel-user"));
//		System.out.println("element: "+element.getText());
//		System.out.println("\n------------\n");
//		
//		List<WebElement> usuarios = element.findElements(By.id("panel-user"));
//		System.out.println("elements: "+usuarios.get(1).getText());	
//	
//		
//		WebElement element2 = driver.findElement(By.id("form-add-user:messages"));
//		System.out.println("element: "+element2.getText());
//		System.out.println("\n------------\n");
//	}
	
	
//	@Test
//	public void testaRemocao() throws InterruptedException {
//		
//	}

	


}
