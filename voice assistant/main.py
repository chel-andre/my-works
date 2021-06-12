import os  # библиотека для работы с ОС
import datetime  # библиотека даты/времени
import sys
import pyttsx3  # библиотека для синтеза речи
import speech_recognition as sr  # библиотека для распознавания речи
import webbrowser  # библиотека для работы с браузером
import speedtest  # библиотека для проверки скорости интернета
from pyowm import OWM  # библиотека для прогноза погоды

def said(words):  # Инициализировать голосовой движок
    tts = pyttsx3.init()
    tts.setProperty('rate', 150)  # Скорость в % (может быть > 100)
    tts.say(words)
    tts.runAndWait()


try:
    said("привет я работаю только с интернетом, перед началом работы я проверю если у вас к нему доступ и скорость")
    test = speedtest.Speedtest()  # проверяем скорость интернета
    download = test.download()  # скорость скачивания
    upload = test.upload()  # скорость загрузки
    dl = (round(((download / 1024) / 1024), 1))
    ul = (round(((upload / 1024) / 1024), 1))
    said("скорость скачивания" + str(dl) + "мегабит в секунду, а скорость загрузки" + str(ul) + "мегабит в секунду")
except:
    said(" ой кажеться у вас нет интернета")
    quit()  # выход из программы при отсутствии интернета


def record_volume():  # Создаем объект на основе библиотеки
    r = sr.Recognizer()  # speech_recognition и вызываем метод для определения данных
    with sr.Microphone(device_index=1) as source:  # Начинаем прослушивать микрофон и записываем данные в source
        said("слушаю")

        r.pause_threshold = 1  # Устанавливаем паузу, чтобы прослушивание # началось лишь по прошествию 1 секунды
        r.adjust_for_ambient_noise(source, duration=1)  # используем adjust_for_ambient_noise для удаления шумов
        audio = r.listen(source)  # Полученные данные записываем в переменную audio # пока мы получили лишь mp3 звук
    said("Услышала")

    try:
        t = r.recognize_google(audio,language='ru-RU').lower()  # Распознаем данные из mp3. Благодаря lower() приводим все в нижний регистр.

        said('Вы сказали:' + t)
        if t == "время":
            dt_now = datetime.datetime.now()
            said('сейчас ' + dt_now.strftime('%H:%M'))
        elif t == "открой файл":
            file_path = r'D:\f.txt'
            os.system("start " + file_path)
        elif t == "ютуб":
            webbrowser.open('https://www.youtube.com/',new=2)  # сли new = 2, открывается новая страница браузера по умолчанию(«вкладка»), если это возможно.
        elif t == "выход":
            sys.exit()
        elif t == "погода":
            owm = OWM('5c737167b7ec1c654f294f8871f61bf8')
            mgr = owm.weather_manager()
            observation = mgr.weather_at_place('Minsk,BY')
            w = observation.weather
            t = w.temperature('celsius')
            t1 = t["temp"]
            a = round(t1)
            said("Температура воздуха в минске" + str(a) + "градусов")

    except:
        said("ошибка")

while True:
    record_volume()