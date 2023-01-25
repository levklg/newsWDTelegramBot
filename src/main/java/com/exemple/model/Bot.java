package com.exemple.model;


import com.exemple.dao.NewsStorageContainer;
import com.exemple.model.Customer;
import com.exemple.model.User;
import com.exemple.model.UserSetting;
import com.exemple.service.ParserImp;
import com.exemple.service.ProcessingMapImp;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.exemple.service.BotServiceImp;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {

    private static BotServiceImp botServiceImp;
    private static ParserImp parserImp;
    private static NewsStorageContainer newsStorageContainer;
    private List<Customer> customerList = new ArrayList<>();
    private HashMap<Long, Customer> mapCustomer;

    public Bot() {
        botServiceImp = new BotServiceImp(this);
        mapCustomer = botServiceImp.getAllCustomer();
        parserImp = new ParserImp();
        newsStorageContainer = new NewsStorageContainer();
    }

    static void pause(int mlsec) {
        try {
            Thread.sleep(mlsec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "newsWDBot";
    }

    @Override
    public String getBotToken() {
        return "5883016412:AAEn37R4wSxiMhqFQ0GDFcvl3eqodgUUsk0";
    }

    @Override
    public void onUpdateReceived(Update update) {

        var msg = update.getMessage();
        var user = msg.getFrom();
        var id = user.getId();
        var textMsg = msg.getText();


        if (mapCustomer.isEmpty() || mapCustomer.get(id) == null) {   // добавляем новогоклиента
            Customer newCustomer = new Customer(id);
            newCustomer.setUserName(user.getUserName());
            mapCustomer.put(id, newCustomer);
        }


        if (msg.isCommand()) {

            if (msg.getText().equals("/start")) {
                botServiceImp.sendText(mapCustomer.get(id).getIdChat(), "Для регистрации отправьте команду /login");
                return;
            }
            if (msg.getText().equals("/help")) {
                botServiceImp.sendText(mapCustomer.get(id).getIdChat(), "Создать логин /login, создать парооль /password");
                return;
            }

            if (msg.getText().equals("/login")) {
                var tCustomer = mapCustomer.get(id);
                tCustomer.setIsLogin(true);
                mapCustomer.put(tCustomer.getIdChat(), tCustomer);

            }

            if (msg.getText().equals("/password")) {
                var tCustomer = mapCustomer.get(id);
                tCustomer.setIsPassword(true);
                mapCustomer.put(tCustomer.getIdChat(), tCustomer);

            }
        }


        // ловим и обрабатываем команды /login, /password
        if (msg.hasText()) {
            if (mapCustomer.get(id).isLogin()) {
                var tCustomer = botServiceImp.login(mapCustomer.get(id), update.getMessage());
                mapCustomer.put(tCustomer.getIdChat(), tCustomer);
                return;
            }
            if (mapCustomer.get(id).isPassword()) {
                var tCustomer = botServiceImp.password(mapCustomer.get(id), update.getMessage());
                mapCustomer.put(tCustomer.getIdChat(), tCustomer);
                return;
            }

            if (mapCustomer.get(id).isLoginInput()) {
                var tCustomer = botServiceImp.loginInput(mapCustomer.get(id), update.getMessage(), mapCustomer);
                mapCustomer.put(tCustomer.getIdChat(), tCustomer);
                return;
            }

            if (mapCustomer.get(id).isPasswordInput()) {
                var tCustomer = botServiceImp.passwordInput(mapCustomer.get(id), update.getMessage(), mapCustomer);
                mapCustomer.put(tCustomer.getIdChat(), tCustomer);
                return;
            }
        }


        return;
    }

    public void processCustomers() {
     //   downloadContent();
        mapCustomer = botServiceImp.getAllCustomer(); //// для тестов потом заменить на changeUserSetting
        if(mapCustomer.size() == 0){
            System.out.println("Список клиентов пустой " + mapCustomer.size());
        }
        System.out.println(mapCustomer.size());
        for (var elementMapCustomer : mapCustomer.entrySet()) {
            sendCustomerNews(elementMapCustomer.getValue());

        }


    }

    public void downloadContent() {    /// Загружаем содержимое сайтов в DAO
        ProcessingMapImp processingMapImp = new ProcessingMapImp();

        Map<String, String> contentSite;

        contentSite = parserImp.parsingKP40();
        if (!processingMapImp.compareMaps(contentSite, newsStorageContainer.getNewsKP40())) { // если полученные новости отличаются от предыдущих
            var mapNewsNow = processingMapImp.getMapOfNewsUpdates(contentSite, newsStorageContainer.getNewsKP40());
            newsStorageContainer.getNowNewsKP40().clear();
            newsStorageContainer.setMapNowKP40(mapNewsNow);
            newsStorageContainer.getNewsKP40().clear();
            newsStorageContainer.setMapKP40(contentSite);// контейнер со свежими новостями
            //  var mapMergeNews = processingMapImp.mergeMapNewsUpdates(contentSite, newsStorageContainer.getNewsKP40()); // добовляем свежие новости в контейнер для новостей
        //    newsStorageContainer.setMapKP40(mapMergeNews);
        }else{
            newsStorageContainer.getNowNewsKP40().clear();
        }

        contentSite = parserImp.parsingRbc();
        if (!processingMapImp.compareMaps(contentSite, newsStorageContainer.getNewsRBC())) {
            var mapNewsNow = processingMapImp.getMapOfNewsUpdates(contentSite, newsStorageContainer.getNewsRBC());
            newsStorageContainer.getNowNewsRBC().clear();
            newsStorageContainer.setMapNowRBC(mapNewsNow);
            newsStorageContainer.getNewsRBC().clear();
            newsStorageContainer.setMapRBC(contentSite);
          //  var mapMergeNews = processingMapImp.mergeMapNewsUpdates(contentSite, newsStorageContainer.getNewsRBC());
         //   newsStorageContainer.setMapRBC(mapMergeNews);
        }else{
            newsStorageContainer.getNowNewsRBC().clear();
        }

        contentSite = parserImp.parsingKalugaPoisk();
        if (!processingMapImp.compareMaps(contentSite, newsStorageContainer.getNewsKalugaPoisk())) {
            var mapNewsNow = processingMapImp.getMapOfNewsUpdates(contentSite, newsStorageContainer.getNewsKalugaPoisk());
            newsStorageContainer.getNowNewsKalugaPoisk().clear();
            newsStorageContainer.setMapNowKalugaPoisk(mapNewsNow);
            newsStorageContainer.getNewsKalugaPoisk().clear();
            newsStorageContainer.setMapKalugaPoisk(contentSite);
        //    var mapMergeNews = processingMapImp.mergeMapNewsUpdates(contentSite, newsStorageContainer.getNewsKalugaPoisk());
        //    newsStorageContainer.setMapKalugaPoisk(mapMergeNews);
        }else{
            newsStorageContainer.getNowNewsKalugaPoisk().clear();
        }

        contentSite = parserImp.parsingNika();
        if (!processingMapImp.compareMaps(contentSite, newsStorageContainer.getNewsNikaTV())) {
            var mapNewsNow = processingMapImp.getMapOfNewsUpdates(contentSite, newsStorageContainer.getNewsNikaTV());
            newsStorageContainer.getNowNewsNikaTV().clear();
            newsStorageContainer.setMapNowNikaTV(mapNewsNow);
            newsStorageContainer.getNewsNikaTV().clear();
            newsStorageContainer.setMapNikaTV(contentSite);
        //    var mapMergeNews = processingMapImp.mergeMapNewsUpdates(contentSite, newsStorageContainer.getNewsNikaTV());
         //   newsStorageContainer.setMapNikaTV(mapMergeNews);
        }else{
            newsStorageContainer.getNowNewsNikaTV().clear();
        }

        contentSite = parserImp.parsingZnamKaluga();
        if (!processingMapImp.compareMaps(contentSite, newsStorageContainer.getNewsZnamKaluga())) {
            var mapNewsNow = processingMapImp.getMapOfNewsUpdates(contentSite, newsStorageContainer.getNewsZnamKaluga());
            newsStorageContainer.getNowNewsZnamKaluga().clear();
            newsStorageContainer.setMapNowZnamKaluga(mapNewsNow);
            newsStorageContainer.getNewsZnamKaluga().clear();
            newsStorageContainer.setMapZnamKaluga(contentSite);
       //     var mapMergeNews = processingMapImp.mergeMapNewsUpdates(contentSite, newsStorageContainer.getNewsZnamKaluga());
        //    newsStorageContainer.setMapZnamKaluga(mapMergeNews);
        }else{
            newsStorageContainer.getNowNewsZnamKaluga().clear();
        }
    }

    public void sendCustomerNews(Customer customer) {  // Отправляем пользователю его контент

        var userSetting = customer.getUser().getUserSetting();
        var listFindString = userSetting.getListFindString();
        var mapWebSites = userSetting.getMapWebSites();
        var userNewsList = wordFilterListPreparation(listFindString, mapWebSites, userSetting.isAllnews());
        var messages = preparingMessage(userNewsList);
        for (var message : messages) {
            System.out.println("Customer " + customer.getUserName());
            System.out.println(message.getText());
      //    botServiceImp.sendText(customer.getIdChat(), message.getText());
            pause(1000);
        }

    }

    public List<String> wordFilterListPreparation(List<String> listFindString, Map<String, String> mapWebSites, boolean allnews) { //Создаем список новостей по фильтру
        List<String> returnList = new ArrayList<>();

        // Если выбрано отправить все новости
        if (allnews) {
            var tmpList = preparingAllNews(mapWebSites);
            if(!tmpList.isEmpty()) {
                returnList.addAll(tmpList);
            }
        }
        if(!listFindString.isEmpty()) {
           var tmpList =  preparingFilterNews(mapWebSites, listFindString);    //Добовляем новости по словам из списка
            if(!tmpList.isEmpty()) {
                returnList.addAll(tmpList);
            }
        }

        return returnList;
    }

    public List<String> preparingAllNews(Map<String, String> mapWebSites) {  // если выбран флажок все новости
        Map<String, String> mapWebSitesLocal = mapWebSites; // карт с сайтами
        List<String> listAllNews = new ArrayList<>();      // возвращаемый список всех новостей
        for (var elementMap : mapWebSites.entrySet()) {
            if (!elementMap.getValue().equals("")) {
                if (elementMap.getKey().equals("rbc")) {
                    var newsMap = newsStorageContainer.getNowNewsRBC();
                    List<String> tmpListNews = new ArrayList<>();
                    tmpListNews.add("start");
                    tmpListNews.add("http://rbc.ru");
                    for (var element : newsMap.entrySet()) {
                        tmpListNews.add(element.getKey());
                    }
                    tmpListNews.add("end");
                    if (tmpListNews.size() > 3) {
                        listAllNews.addAll(tmpListNews);
                    }
                }
                if (elementMap.getKey().equals("kp40")) {
                    var newsMap = newsStorageContainer.getNowNewsKP40();
                    List<String> tmpListNews = new ArrayList<>();
                    tmpListNews.add("start");
                    tmpListNews.add("https://www.kp40.ru");
                    for (var element : newsMap.entrySet()) {
                        tmpListNews.add(element.getKey());
                    }
                    tmpListNews.add("end");
                    if (tmpListNews.size() > 3) {
                        listAllNews.addAll(tmpListNews);
                    }
                }
                if (elementMap.getKey().equals("kaluga-poisk")) {
                    var newsMap = newsStorageContainer.getNowNewsKalugaPoisk();
                    List<String> tmpListNews = new ArrayList<>();
                    tmpListNews.add("start");
                    tmpListNews.add("https://www.kaluga-poisk.ru/news");
                    for (var element : newsMap.entrySet()) {
                        tmpListNews.add(element.getKey());
                    }
                    tmpListNews.add("end");
                    if (tmpListNews.size() > 3) {
                        listAllNews.addAll(tmpListNews);
                    }
                }
                if (elementMap.getKey().equals("nikatv")) {
                    var newsMap = newsStorageContainer.getNowNewsNikaTV();
                    List<String> tmpListNews = new ArrayList<>();
                    tmpListNews.add("start");
                    tmpListNews.add("https://nikatv.ru/news/lenta");
                    for (var element : newsMap.entrySet()) {
                        tmpListNews.add(element.getKey());
                    }
                    tmpListNews.add("end");
                    if (tmpListNews.size() > 3) {
                        listAllNews.addAll(tmpListNews);
                    }
                }
                if (elementMap.getKey().equals("znamkaluga")) {
                    var newsMap = newsStorageContainer.getNowNewsZnamKaluga();
                    List<String> tmpListNews = new ArrayList<>();
                    tmpListNews.add("start");
                    tmpListNews.add("https://znamkaluga.ru/category/materialy/news/");
                    for (var element : newsMap.entrySet()) {
                        tmpListNews.add(element.getKey());
                    }
                    tmpListNews.add("end");
                    if (tmpListNews.size() > 3) {
                        listAllNews.addAll(tmpListNews);
                    }
                }
            }
        }
        return listAllNews;
    }

    public List<String> preparingFilterNews(Map<String, String> mapWebSites, List<String> listFindString) { // поиск по заданным словам в фильтре
        Map<String, String> mapWebSitesLocal = mapWebSites;// карта с сайтами
        List<String> listFilterNews = new ArrayList<>();//  список со словами из фильтра
        for (var elementMapWebSites : mapWebSites.entrySet()) {
            if (!elementMapWebSites.getValue().equals("")) {
                if (elementMapWebSites.getKey().equals("rbc")) {
                    var newsMap = newsStorageContainer.getNowNewsRBC();
                    List<String> tmpListNews = new ArrayList<>();
                    tmpListNews.add("start");
                    tmpListNews.add("http://rbc.ru");

                    for (var elementNewsMap : newsMap.entrySet()) {
                        for (var s : listFindString) {
                            if (StringUtils.containsIgnoreCase(elementNewsMap.getKey(), s)) {
                                tmpListNews.add(elementNewsMap.getKey());
                            }
                        }
                        //
                    }
                    tmpListNews.add("end");
                    if (tmpListNews.size() > 3) {
                        listFilterNews.addAll(tmpListNews);
                    }
                }
                if (elementMapWebSites.getKey().equals("kp40")) {
                    var newsMap = newsStorageContainer.getNowNewsKP40();
                    List<String> tmpListNews = new ArrayList<>();
                    tmpListNews.add("start");
                    tmpListNews.add("https://www.kp40.ru");
                    for (var elementNewsMap : newsMap.entrySet()) {
                        for (var s : listFindString) {
                            if (StringUtils.containsIgnoreCase(elementNewsMap.getKey(), s)) {
                                tmpListNews.add(elementNewsMap.getKey());
                            }
                        }
                    }
                    tmpListNews.add("end");
                    if (tmpListNews.size() > 3) {
                        listFilterNews.addAll(tmpListNews);
                    }
                }
                if (elementMapWebSites.getKey().equals("kaluga-poisk")) {
                    var newsMap = newsStorageContainer.getNowNewsKalugaPoisk();
                    List<String> tmpListNews = new ArrayList<>();
                    tmpListNews.add("start");
                    tmpListNews.add("https://www.kaluga-poisk.ru/news");
                    for (var elementNewsMap : newsMap.entrySet()) {
                        for (var s : listFindString) {
                            if (StringUtils.containsIgnoreCase(elementNewsMap.getKey(), s)) {
                                tmpListNews.add(elementNewsMap.getKey());
                            }
                        }
                    }
                    tmpListNews.add("end");
                    if (tmpListNews.size() > 3) {
                        listFilterNews.addAll(tmpListNews);
                    }
                }
                if (elementMapWebSites.getKey().equals("nikatv")) {
                    var newsMap = newsStorageContainer.getNowNewsNikaTV();
                    List<String> tmpListNews = new ArrayList<>();
                    tmpListNews.add("start");
                    tmpListNews.add("https://nikatv.ru/news/lenta");
                    for (var elementNewsMap : newsMap.entrySet()) {
                        for (var s : listFindString) {
                            if (StringUtils.containsIgnoreCase(elementNewsMap.getKey(), s)) {
                                tmpListNews.add(elementNewsMap.getKey());
                            }
                        }
                    }
                    tmpListNews.add("end");
                    if (tmpListNews.size() > 3) {
                        listFilterNews.addAll(tmpListNews);
                    }
                }
                if (elementMapWebSites.getKey().equals("znamkaluga")) {
                    var newsMap = newsStorageContainer.getNowNewsZnamKaluga();
                    List<String> tmpListNews = new ArrayList<>();
                    tmpListNews.add("start");
                    tmpListNews.add("https://znamkaluga.ru/category/materialy/news/");
                    for (var elementNewsMap : newsMap.entrySet()) {
                        for (var s : listFindString) {
                            if (StringUtils.containsIgnoreCase(elementNewsMap.getKey(), s)) {
                                tmpListNews.add(elementNewsMap.getKey());
                            }
                        }
                    }
                    tmpListNews.add("end");
                    if (tmpListNews.size() > 3) {
                        listFilterNews.addAll(tmpListNews);
                    }
                }
            }
        }
        return listFilterNews;
    }

    public List<Message> preparingMessage(List<String> userNewsList) {  // готовим список с сообщениями для пользователя
        List<Message> returnListMesseges = new ArrayList<>();
        for (int i = 0; i < userNewsList.size(); i++) {
            if (userNewsList.get(i).equals("start")) {
                int index = i + 1;
                Message message = new Message();
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    if (userNewsList.get(index).equals("end")) {
                        break;
                    }
                    if (!userNewsList.get(index).equals("end")) {
                        stringBuilder = stringBuilder.append((userNewsList.get(index))).append("\n");
                    }
                    index++;
                }
                message.setText(stringBuilder.toString());
                returnListMesseges.add(message);
            }
        }

        return returnListMesseges;
    }

}