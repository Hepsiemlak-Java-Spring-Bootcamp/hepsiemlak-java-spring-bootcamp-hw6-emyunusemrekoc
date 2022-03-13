package main.java.emlakburada.service;

import java.util.ArrayList;
import java.util.List;

import emlakburada.client.request.AddressRequest;
import emlakburada.client.request.BannerRequest;
import emlakburada.repository.FavoriteAdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emlakburada.client.BannerClient;
import emlakburada.dto.AdvertRequest;
import emlakburada.dto.response.AdvertResponse;
import emlakburada.model.Advert;
import emlakburada.model.User;
import emlakburada.queue.QueueService;
import emlakburada.repository.AdvertRepository;
import emlakburada.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class AdvertService {

    @Autowired
    private AdvertRepository advertRepository;

    @Autowired
    private FavoriteAdvertRepository favoriteAdvertRepository;


    private static int advertNo = 38164784;

    @Autowired
    private BannerClient bannerClient;

    @Autowired
    private QueueService rabbitMqService;

    @Autowired
    private QueueService activeMqService;
    @Autowired
    private UserRepository userRepository;

    public List<AdvertResponse> getAllAdverts() {
        List<AdvertResponse> advertList = new ArrayList<>();
        for (Advert advert : advertRepository.findAll()) {
            advertList.add(convertToAdvertResponse(advert));
        }
        return advertList;
    }

    public AdvertResponse saveAdvert(AdvertRequest request) {

        Advert savedAdvert = advertRepository.save(convertToAdvert(request));
        EmailMessage emailMessage = new EmailMessage(request.getUserId(),"emyunusemrekoc@gmail.com");
        //String mail = "emyunusemrekoc@gmail.com" + "," + request.getUserId();
         rabbitMqService.sendMessage(emailMessage.toString());
//      activeMqService.sendMessage(mail);
        bannerClient.saveBanner(prepareBannerRequest(0));

        return convertToAdvertResponse(savedAdvert);

    }


    public AdvertResponse updateAdvertById(AdvertRequest request, int advertId) {

        Advert advert = advertRepository.findById(advertId);
        advert.setTitle(request.getTitle());
        advert.setPrice(request.getPrice());

        Advert savedAdvert = advertRepository.save(advert);
//        EmailMessage emailMessage = new EmailMessage("emyunusemrekoc@gmail.com");

        //rabbitMqService.sendMessage(emailMessage);
        //activeMqService.sendMessage(emailMessage);
        //bannerClient.saveBanner();

        return convertToAdvertResponse(savedAdvert);

    }

    public String deleteAdvertById(int advertId) {
        Advert advert = advertRepository.findById(advertId);
        if (advert == null) {
            return "bu id ye ait ilan bulunamadı";
        } else {
            String message = advert.getTitle() + " başlıklı ilan silindi.";
            advertRepository.deleteById(advertId);
            return message;

        }

    }


    public AdvertResponse findAdvertByAdvertId(int advertId) {
        Advert advert = advertRepository.findById(advertId);
        return convertToAdvertResponse(advert);
    }

    private AdvertResponse convertToAdvertResponse(Advert savedAdvert) {
        AdvertResponse response = new AdvertResponse();
        response.setTitle(savedAdvert.getTitle());
        response.setPrice(savedAdvert.getPrice());
        response.setAdvertNo(savedAdvert.getAdvertNo());
        //response.setUser(savedAdvert.getUser());
        return response;
    }

    private Advert convertToAdvert(AdvertRequest request) {

        User user = userRepository.findById(request.getUserId());
        Advert advert = new Advert();
        advertNo++;

        advert.setUser(user);
        advert.setAdvertNo(advertNo);
        advert.setTitle(request.getTitle());
        advert.setPrice(request.getPrice());


        return advert;
    }

    private BannerRequest prepareBannerRequest(int advertNo) {
        BannerRequest bannerRequest = new BannerRequest();
        bannerRequest.setAdvertNo(advertNo);
        bannerRequest.setPhone("555");
        bannerRequest.setTotal(1);
        bannerRequest.setAddress(new AddressRequest("istanbul", "kadıköy", "acik adres"));
        return bannerRequest;
    }


}
