require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start || sessionResult = "Сценарий начинается отсюда", sessionResultColor = "#143AD1"
        q!: $regex</start>
        image: https://allfest.ru/sites/default/files/styles/af_1200x798/public/2023-09/dvizh-fest.jpg?itok=aJKNaHii
        a: Привет! Надеюсь, ты ознакомился с информацией о наших компаниях. Осталось проверить, насколько хорошо ты всё запомнил: отвечай на вопросы и получи наклейку в свой паспорт участника.|| htmlEnabled = true, html = "<b>Привет!</b> <br><br>Надеюсь, ты ознакомился с <b>информацией о наших компаниях</b>. Осталось проверить, насколько хорошо ты всё запомнил: <b>отвечай на вопросы и получи наклейку в свой паспорт участника.</b>"
        buttons:
            "Старт" -> /Старт
        intent: /Статус заказа || onlyThisState = false, toState = "/Статус заказа"
        intent: /sys/ru/aimylogic/parting || onlyThisState = false, toState = "/Bye"

    state: Bye
        a: Пока-пока!
        EndSession:
            actions = {}

    state: Ввод ФИО || sessionResult = "Старт", sessionResultColor = "#7E47D1"
         a: Скажите, пожалуйста, ваши полные ФИО? || htmlEnabled = false, html = "Скажите, пожалуйста, ваши полные ФИО?"
         intent: /ФИО || onlyThisState = false, toState = "/ФИО сохранено"
        event: noMatch || onlyThisState = false, toState = "/Неправильный ввод"

    state: ФИО сохранено || sessionResult = "Старт", sessionResultColor = "#7E47D1"
        a: Отлично! А теперь подскажи, в какой компании ты работаешь? Выбери из предложенных вариантов. Если ты пришёл поддержать своего близкого человека, выбирай кнопку «гость». || htmlEnabled = false, html = "Отлично! А теперь подскажи, в какой компании ты работаешь? Выбери из предложенных вариантов. Если ты пришёл поддержать своего близкого человека, выбирай кнопку <b>«гость»</b>."
        buttons:
            "Старт" -> /Старт
            "СУЭК" -> /СУЭК
            "СГК" -> /СГК"
            "ЕвроХим" -> /ЕвроХим
            "ГРК" -> /ГРК
            "НТК" -> /НТК
            "Портовый Альянс" -> /Портовый Альянс
            "ЦТИП" -> /ЦТИП
            "ФМ" -> /ФМ
            "Гость" -> /Гость
        go!: /Сохранение ФИО
        
        state: Сохранение ФИО || sessionResult = "Старт", sessionResultColor = "#7E47D1"
    a: Отлично, {{$request.query}}|| htmlEnabled = false
    go!: /Следующий шаг
        
    state: Ошибка получения || sessionResult = "Сервер недоступен", sessionResultColor = "#CD4C2B"
        a: К сожалению, я никак не могу связаться с сервером. Попробуйте позже, пожалуйста. || htmlEnabled = false, html = "К сожалению, я никак не могу связаться с сервером. Попробуйте позже, пожалуйста."
        go!: /Меню

    state: Неправильное ФИО || sessionResult = "Старт", sessionResultColor = "#7E47D1"
        a: Это не похоже на полные ФИО. Пожалуйста, введите ФИО полностью. || htmlEnabled = false, html = "Это не похоже на полные ФИО. Пожалуйста, введите ФИО полностью."
        go!: /Ввод ФИО

    state: Получили данные || sessionResult = "Здесь надо будет научить бота обрабатывать ответ сервера", sessionResultColor = "#CD4C2B"
        a: Кажется, я не поняла, что мне ответили. Научите меня разбираться с этим, пожалуйста :) || htmlEnabled = false, html = "Кажется, я не поняла, что мне ответили. Научите меня разбираться с этим, пожалуйста :)"
        go!: /Меню
