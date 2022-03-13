package test.java.emlakburada.service;

import emlakburada.dto.request.BannerRequest;
import emlakburada.dto.response.BannerResponse;
import emlakburada.model.Banner;
import emlakburada.repository.BannerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest
public class BannerServiceTest {

    @InjectMocks
    private BannerService bannerService;

    @Mock
    private BannerRepository bannerRepository;

    @BeforeEach
    void setup() {


        Mockito
                .when(bannerRepository.findAll())
                .thenReturn(prepareMockBannerList());


    }

    @Test
    void findAll(){

        List<BannerResponse> allBanners = bannerService.getAllBanners();

        assertNotNull(allBanners);

        assertThat(allBanners.size()).isNotZero();

    }

    @Test
    void saveBanner() {


        Mockito
                .when(bannerRepository.save(any()))
                .thenReturn(prepareBanner(10));

        BannerResponse response= bannerService.saveBanner(prepareBannerRequest());
        assertNotNull(response);
        assertEquals(10,response.getAdvertNo());
        assertEquals("5552556565",response.getPhone());
        assertEquals(100,response.getTotal());

    }

    @Test
    void  updateBannerById(){

        BannerRequest request = prepareBannerRequest();
        Banner banner =prepareBanner(10);

        Mockito
                .when(bannerRepository.findById(0))
                .thenReturn(banner);

        Mockito
                .when(bannerRepository.save(banner))
                .thenReturn(prepareBanner(11));




        BannerResponse response = bannerService.updateBannerById(request,0);

        assertNotNull(response);
        assertEquals(11, response.getAdvertNo());
        assertEquals("5552556565", response.getPhone());
        assertEquals(100,response.getTotal());
    }

    @Test
    void deleteBannerById(){
        Banner banner =prepareBanner(10);

        Mockito
                .when(bannerRepository.findById(0))
                .thenReturn(banner);

        String response = bannerService.deleteBannerById(0);
        Mockito.verify(bannerRepository).deleteById(any());
        assertThat(response).isNotEmpty();


    }

    private List<Banner> prepareMockBannerList() {
        List<Banner> userList = new ArrayList<Banner>();
        userList.add(prepareBanner(10));
        userList.add(prepareBanner(11));
        userList.add(prepareBanner(12));
        return userList;
    }

    private Banner prepareBanner(int advertNo) {
        return new Banner(advertNo,"5552556565",100);

    }

    private BannerRequest prepareBannerRequest() {
        return new BannerRequest(0, "5245242424",100);
    }
}
