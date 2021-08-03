from selenium import webdriver
import time

options = webdriver.ChromeOptions()
options.binary_location = "C:\\Program Files\\Google\\Chrome Beta\\Application\\chrome.exe"
chrome_driver_binary = "C:\\chromedriver_win32\\chromedriver.exe"
driver = webdriver.Chrome(chrome_driver_binary, chrome_options=options)

driver.set_window_size(1920,1080)
driver.get("https://csssr.github.io/qa-engineer/")
time.sleep(1)
driver.execute_script("window.scrollTo(0, 1080)")
time.sleep(1)
a = driver.find_element_by_xpath("/html/body/div[1]/section[2]/section/div[2]/a").click()
time.sleep(1)
b = driver.find_element_by_xpath("/html/body/div[1]/section[3]/div[2]/aside/ul/li[4]/label/a").get_attribute('href')
if b=="https://getsharex.com/":
    print("Passed")
else:
    print("Failed")
driver.close()
