package main.java.emlakburada.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emlakburada.dto.request.BannerRequest;
import emlakburada.dto.response.BannerResponse;
import emlakburada.model.Banner;
import emlakburada.repository.BannerRepository;

@Service
public class BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    public List<BannerResponse> getAllBanners() {
        List<BannerResponse> bannerResponses = new ArrayList<>();
        for (Banner banner : bannerRepository.findAll()) {
            bannerResponses.add(convertToBannerResponse(banner));
        }
        return bannerResponses;
    }

    public BannerResponse saveBanner(BannerRequest request) {
        Banner banner = bannerRepository.save(convertToBanner(request));
        return convertToBannerResponse(banner);
    }

    public BannerResponse updateBannerById(BannerRequest request, int bannerId) {

        Banner banner = bannerRepository.findById(bannerId);
        banner.setAdvertNo(request.getAdvertNo());
        banner.setTotal(request.getTotal());
        banner.setPhone(request.getPhone());

        Banner savedBanner = bannerRepository.save(banner);

        return convertToBannerResponse(savedBanner);

    }


    public String deleteBannerById(int bannerId) {
        Banner banner = bannerRepository.findById(bannerId);
        if (banner == null) {
            return "bu id ye ait mesaj bulunamadı";
        } else {
            String info = banner.getAdvertNo() + " ilan numaralı broşür silindi.";
            bannerRepository.deleteById(bannerId);
            return info;

        }
    }

    private BannerResponse convertToBannerResponse(Banner banner) {
        BannerResponse response = new BannerResponse();
        response.setAdvertNo(banner.getAdvertNo());
        response.setPhone(banner.getPhone());
        response.setTotal(banner.getTotal());
        return response;
    }

    private Banner convertToBanner(BannerRequest request) {
        Banner banner = new Banner();
        banner.setAdvertNo(request.getAdvertNo());
        banner.setPhone(request.getPhone());
        banner.setTotal(request.getTotal());
        return banner;
    }

}
