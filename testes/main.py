from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.firefox.options import Options
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

URL = "http://localhost:9080/portal-apoio-stg"

def teste_login():
    print ("Executando teste de login...")
    firefox_options = Options()

    driver = webdriver.Firefox(options=firefox_options)
    driver.get(URL)
    assert "Portal de Apoio" in driver.title

    elem = driver.find_element(By.NAME, "username")
    elem.clear()
    elem.send_keys("admin")

    elem = driver.find_element(By.NAME, "password")
    elem.clear()
    elem.send_keys("admin")

    elem = driver.find_element(By.ID, "btnLogin")
    elem.click()
    assert "<h1>Portal de Apoio</h1>" in driver.page_source

    driver.close()

    print ("Teste executado com sucesso!")

def teste_falha_login():
    print ("Executando teste de erro de login...")
    firefox_options = Options()

    driver = webdriver.Firefox(options=firefox_options)
    driver.get(URL)
    assert "Portal de Apoio" in driver.title

    elem = driver.find_element(By.NAME, "username")
    elem.clear()
    elem.send_keys("admin")

    elem = driver.find_element(By.NAME, "password")
    elem.clear()
    elem.send_keys("123")

    elem = driver.find_element(By.ID, "btnLogin")
    elem.click()
    assert "Usuário/senha inválidos." in driver.page_source

    driver.close()

    print ("Teste executado com sucesso!")


def teste_cadastro_bolsa_produtividade():
    print ("Executando teste de cadastro de bolsa...")
    firefox_options = Options()

    driver = webdriver.Firefox(options=firefox_options)
    driver.get(URL)
    assert "Portal de Apoio" in driver.title

    elem = driver.find_element(By.NAME, "username")
    elem.clear()
    elem.send_keys("admin")

    elem = driver.find_element(By.NAME, "password")
    elem.clear()
    elem.send_keys("admin")

    elem = driver.find_element(By.ID, "btnLogin")
    elem.click()
    assert "<h1>Portal de Apoio</h1>" in driver.page_source

    driver.get(URL + "/pesquisa/administracao/bolsas-produtividade-cnpq.jsf")
    assert "<h2>Bolsas de Produtividade CNPQ</h2>" in driver.page_source

    elems = driver.find_elements(By.XPATH, "//tbody[@id='formBolsasProdutividade:tblBolsasProdutividade_data']/tr/td")
    encontrado = False
    for e in elems:
        if e.text == 'BOLSA 1':
            encontrado = True
            break

    assert encontrado

    botao = driver.find_element(By.ID, "formBolsasProdutividade:btnNova")
    botao.click()

    elem = WebDriverWait(driver, 10).until(
        EC.visibility_of_element_located((By.ID, "dialogBolsaProdutividade_title"))
    )
    assert elem.text == "Bolsa de Produtividade CNPQ"

    descricao = driver.find_element(By.NAME, "formBolsaProdutividade:descricao")
    descricao.clear()
    descricao.send_keys("TESTE 2")

    salvar = driver.find_element(By.NAME, "formBolsaProdutividade:btnSalva")
    salvar.click()

    elem = WebDriverWait(driver, 10).until(
        EC.visibility_of_element_located((By.XPATH, "//div[@class='ui-growl-message']/span[@class='ui-growl-title']"))
    )
    assert elem.text == "Dados salvos com sucesso!"

    elems = driver.find_elements(By.XPATH, "//tbody[@id='formBolsasProdutividade:tblBolsasProdutividade_data']/tr/td")
    encontrado = False
    for e in elems:
        if e.text == 'TESTE 2':
            encontrado = True
            break

    assert encontrado

    print ("Teste executado com sucesso!")

    driver.close()


def teste_cadastro_bolsa_produtividade_duplicada():
    print ("Executando teste de cadastro de bolsa...")
    firefox_options = Options()

    driver = webdriver.Firefox(options=firefox_options)
    driver.get(URL)
    assert "Portal de Apoio" in driver.title

    elem = driver.find_element(By.NAME, "username")
    elem.clear()
    elem.send_keys("admin")

    elem = driver.find_element(By.NAME, "password")
    elem.clear()
    elem.send_keys("admin")

    elem = driver.find_element(By.ID, "btnLogin")
    elem.click()
    assert "<h1>Portal de Apoio</h1>" in driver.page_source

    driver.get(URL + "/pesquisa/administracao/bolsas-produtividade-cnpq.jsf")
    assert "<h2>Bolsas de Produtividade CNPQ</h2>" in driver.page_source

    elems = driver.find_elements(By.XPATH, "//tbody[@id='formBolsasProdutividade:tblBolsasProdutividade_data']/tr/td")
    encontrado = False
    for e in elems:
        if e.text == 'BOLSA 1':
            encontrado = True
            break

    assert encontrado

    botao = driver.find_element(By.ID, "formBolsasProdutividade:btnNova")
    botao.click()

    elem = WebDriverWait(driver, 10).until(
        EC.visibility_of_element_located((By.ID, "dialogBolsaProdutividade_title"))
    )
    assert elem.text == "Bolsa de Produtividade CNPQ"

    descricao = driver.find_element(By.NAME, "formBolsaProdutividade:descricao")
    descricao.clear()
    descricao.send_keys("BOLSA 1")

    salvar = driver.find_element(By.NAME, "formBolsaProdutividade:btnSalva")
    salvar.click()

    elem = WebDriverWait(driver, 10).until(
        EC.visibility_of_element_located((By.XPATH, "//div[@class='ui-growl-message']/span[@class='ui-growl-title']"))
    )
    assert elem.text == "Já existe uma bolsa cadastrada com esta descrição."

    print ("Teste executado com sucesso!")

    driver.close()


if __name__ == "__main__":
    teste_login()
    teste_falha_login()
    teste_cadastro_bolsa_produtividade()
    teste_cadastro_bolsa_produtividade_duplicada()
