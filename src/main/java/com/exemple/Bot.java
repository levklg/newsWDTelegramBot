package com.exemple;


import com.exemple.dao.NewsStorageContainer;
import com.exemple.model.Customer;
import com.exemple.service.ParserImp;
import com.exemple.service.ProcessingMapImp;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.exemple.service.BotServiceImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {

    private static BotServiceImp botServiceImp;
    private List<Customer> customerList = new ArrayList<>();
    private HashMap<Long, Customer> mapCustomer;
    private static ParserImp parserImp;
    private static NewsStorageContainer newsStorageContainer;

    public Bot() {
        botServiceImp = new BotServiceImp(this);
        mapCustomer = botServiceImp.getAllCustomer();
        parserImp = new ParserImp();
        newsStorageContainer = new NewsStorageContainer();
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
            if (msg.getText().equals("/start")) {
                botServiceImp.sendText(mapCustomer.get(id).getIdChat(), "Создать логин /login, создать парооль /password");
                return;
            }


            if (msg.getText().equals("/login")) {
                var tCustomer = mapCustomer.get(id);
                tCustomer.setIsLogin(true);
                mapCustomer.put(tCustomer.getIdChat(), tCustomer);
                return;
            }

            if (msg.getText().equals("/password")) {
                var tCustomer = mapCustomer.get(id);
                tCustomer.setIsPassword(true);
                mapCustomer.put(tCustomer.getIdChat(), tCustomer);
                return;
            }
        }else{
            botServiceImp.sendText(mapCustomer.get(id).getIdChat(),"Команда не распознана.");
        }

        // ловим и обрабатываем команды /login, /password
        if(msg.hasText()) {
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



    public void processCustomers(){
      downloadContent();

      for (var elementMap : mapCustomer.entrySet()){
          sendCustomerNews(elementMap.getValue());

      }


    }


    public  void downloadContent(){
        ProcessingMapImp processingMapImp = new ProcessingMapImp();

        Map<String, String> contentSite;

        contentSite =  parserImp.parsingKP40();
        if( !processingMapImp.compareMaps(contentSite, newsStorageContainer.getNewsKP40())){
            var mapNewsNow  =  processingMapImp.getMapOfNewsUpdates(contentSite, newsStorageContainer.getNewsKP40());
            newsStorageContainer.setMapNowKP40(mapNewsNow);
            var mapMergeNews = processingMapImp.mergeMapNewsUpdates(contentSite, newsStorageContainer.getNewsKP40());
            newsStorageContainer.setMapKP40(mapMergeNews);
        }else{
            newsStorageContainer.setMapKP40(contentSite);
        }

        contentSite = parserImp.parsingRbc();
        if( !processingMapImp.compareMaps(contentSite, newsStorageContainer.getNewsRBC())){
            var mapNewsNow  =  processingMapImp.getMapOfNewsUpdates(contentSite, newsStorageContainer.getNewsRBC());
            newsStorageContainer.setMapNowRBC(mapNewsNow);
            var mapMergeNews = processingMapImp.mergeMapNewsUpdates(contentSite, newsStorageContainer.getNewsRBC());
            newsStorageContainer.setMapRBC(mapMergeNews);
        }else {
            newsStorageContainer.setMapRBC(contentSite);
        }

        contentSite = parserImp.parsingKalugaPoisk();
        if( !processingMapImp.compareMaps(contentSite, newsStorageContainer.getNewsKalugaPoisk())){
            var mapNewsNow  =  processingMapImp.getMapOfNewsUpdates(contentSite, newsStorageContainer.getNewsKalugaPoisk());
            newsStorageContainer.setMapNowKalugaPoisk(mapNewsNow);
            var mapMergeNews = processingMapImp.mergeMapNewsUpdates(contentSite, newsStorageContainer.getNewsKalugaPoisk());
            newsStorageContainer.setMapKalugaPoisk(mapMergeNews);
        }else {
            newsStorageContainer.setMapKalugaPoisk(contentSite);
        }

        contentSite = parserImp.parsingNika();
        if( !processingMapImp.compareMaps(contentSite, newsStorageContainer.getNewsNikaTV())){
            var mapNewsNow  =  processingMapImp.getMapOfNewsUpdates(contentSite, newsStorageContainer.getNewsNikaTV());
            newsStorageContainer.setMapNowNikaTV(mapNewsNow);
            var mapMergeNews = processingMapImp.mergeMapNewsUpdates(contentSite, newsStorageContainer.getNewsNikaTV());
            newsStorageContainer.setMapNikaTV(mapMergeNews);
        }else {
            newsStorageContainer.setMapNikaTV(contentSite);
        }

        contentSite = parserImp.parsingZnamKaluga();
        if( !processingMapImp.compareMaps(contentSite, newsStorageContainer.getNewsZnamKaluga())){
            var mapNewsNow  =  processingMapImp.getMapOfNewsUpdates(contentSite, newsStorageContainer.getNewsZnamKaluga());
            newsStorageContainer.setMapNowZnamKaluga(mapNewsNow);
            var mapMergeNews = processingMapImp.mergeMapNewsUpdates(contentSite, newsStorageContainer.getNewsZnamKaluga());
            newsStorageContainer.setMapZnamKaluga(mapMergeNews);
        }else {
            newsStorageContainer.setMapZnamKaluga(contentSite);
        }
    }

    public void sendCustomerNews(Customer customer){
     var userSetting = customer.getUser().getUserSetting();
     var listFindString = userSetting.getListFindString();
     var mapWebSites = userSetting.getMapWebSites();
     var userNewsList = wordFilterListPreparation(listFindString, mapWebSites);
     var messages = preparingMessage(userNewsList);
     for(var message : messages){
            botServiceImp.sendText(customer.getIdChat(),message);
            pause(1000);
        }

    }

    public List<String> wordFilterListPreparation(List<String> listFindString, Map<String, String> mapWebSites){
        return null;
    }

    public List<String> preparingMessage (List<String> userNewsList){
        return null;
    }

    static void pause(int mlsec){
        try {
            Thread.sleep(mlsec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}