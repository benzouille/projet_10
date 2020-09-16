package fr.biblioc.bibliocreservation.web.controller;

import fr.biblioc.bibliocreservation.dao.PreReservationDao;
import fr.biblioc.bibliocreservation.dto.PreReservationDto;
import fr.biblioc.bibliocreservation.mapper.PreReservationMapperImpl;
import fr.biblioc.bibliocreservation.model.PreReservation;
import fr.biblioc.bibliocreservation.web.exceptions.FunctionalException;
import org.jetbrains.annotations.NotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PrereservationControllerTest {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Spy
    @InjectMocks
    PreReservationController preReservationController;

    @Mock
    PreReservationDao preReservationDao;

    @Mock
    PreReservationMapperImpl mapper;

    @Test
    public void listeDesPreReservations_should_return_a_list() throws FunctionalException {
        //GIVEN
        List<PreReservation> preReservationList = getPreReservationsDummy();
        List<PreReservationDto> preReservationListDto = getPreReservationDtosDummy();

        when(preReservationController.getPrereservationDao()).thenReturn(preReservationList);

        doReturn(preReservationListDto).when(preReservationController).getPreReservationDtos(preReservationList);

        //WHEN
        List<PreReservationDto> preReservationDtoList = preReservationController.listeDesPreReservations();

        //THEN
        assertThat(preReservationController.listeDesPreReservations().get(1).getId_compte()).isEqualTo(1);
        //assertThat(preReservationDtoList.get(1).getId_compte()).isEqualTo(1);
    }


    public void getPrereservationDaoById_compte() throws FunctionalException {
        //GIVEN
        int id_compte = 1;
        List<PreReservation> preReservationList = getPreReservationsDummy();



    }

    //TODO A revoir
    @Test
    public void listPreReservationsByIdUser_should_return_a_list() throws FunctionalException {
        //GIVEN
        int id_compte = 1;
        List<PreReservation> preReservationList = getPreReservationsDummy();
        List<PreReservationDto> preReservationListDto = getPreReservationDtosDummy();

        preReservationController.setPreReservationMapper(mapper);

        when(preReservationController.getPrereservationDaoById_compte(id_compte)).thenReturn(preReservationList);
        //when(preReservationController.getPreReservationDtos(preReservationList)).thenReturn(preReservationListDto);
        doReturn(preReservationListDto).when(preReservationController).getPreReservationDtos(preReservationList);


        //WHEN
        List<PreReservationDto> preReservationDtoList = preReservationController.listPreReservationsByIdUser(id_compte);

        //THEN
        assertThat(preReservationController.listeDesPreReservations().get(1).getId_compte()).isEqualTo(1);
    }

    //TODO A revoir
    @Test
    public void listPreReservationsByIdUser_should_return_exception() throws FunctionalException {
        //GIVEN
        int id_compte = 1;
        List<PreReservation> preReservationList = getPreReservationsDummy();
        List<PreReservationDto> preReservationListDto = getPreReservationDtosDummy();

        when(preReservationController.getPrereservationDaoById_compte(id_compte)).thenReturn(preReservationList);
        //when(preReservationController.getPreReservationDtos(preReservationList)).thenReturn(preReservationListDto);
        doReturn(preReservationListDto).when(preReservationController).getPreReservationDtos(preReservationList);


        //WHEN
        List<PreReservationDto> preReservationDtoList = preReservationController.listPreReservationsByIdUser(id_compte);

        //THEN
        //assertThat(preReservationController.listeDesPreReservations().get(1).getId_compte()).isEqualTo(1);
        Assertions.assertThrows(FunctionalException.class, () -> preReservationController.listPreReservationsByIdUser(id_compte));
    }

    @Test
    public void addPreReservation_should_add_to_list(){}

    @Test
    public void getPreReservationDto_should_return_PreReservationDto() throws FunctionalException {
        PreReservation preReservation = new PreReservation(1, Date.from(Instant.now()), 1, true, true);
        PreReservationDto preReservationDto = new PreReservationDto(1, 1, Date.from(Instant.now()), 1, true, true);

        when(preReservationController.getPreReservationDto(java.util.Optional.of(preReservation))).thenReturn(preReservationDto);

        preReservationController.getPreReservationDto(java.util.Optional.of(preReservation));

        assertThat(preReservationController.getPreReservationDto(java.util.Optional.of(preReservation))).isEqualTo(preReservationDto);
    }

    @Test
    public void getPreReservationDtos_should_return_PreReservationDtoList() throws FunctionalException {
        List<PreReservation> preReservationList = getPreReservationsDummy();
        List<PreReservationDto> preReservationDtoList = getPreReservationDtosDummy();
        preReservationController.setPreReservationMapper(mapper);

        when(preReservationController.getPreReservationDtos(preReservationList)).thenReturn(preReservationDtoList);

        preReservationController.getPreReservationDtos(preReservationList);

        assertThat(preReservationController.getPreReservationDtos(preReservationList)).isEqualTo(preReservationDtoList);
    }

    @Test
    public void getPreReservationDtos_should_be_empty() throws FunctionalException {
        List<PreReservation> vide = new ArrayList<>();
        Assertions.assertThrows(FunctionalException.class, () -> preReservationController.getPreReservationDtos(vide));
    }

    @Test
    public void expirationDateCheck_should_return_true(){
        //GIVEN
        Date dateToTest = new Date(1988, 11, 2);

        //WHEN
        boolean expired = preReservationController.expirationDateCheck(dateToTest);

        //THEN
        assertThat(expired).isEqualTo(true);
    }

    @Test
    public void expirationDateCheck_should_return_false(){
        //GIVEN
        Date dateToTest = new Date(2020, 11, 2);

        //WHEN
        boolean expired = preReservationController.expirationDateCheck(dateToTest);

        //THEN
        assertThat(expired).isEqualTo(false);
    }

    @Test
    public void getExpiredMailSendPreReservation_should_return_list(){

        //GIVEN
        PreReservation preReservation = new PreReservation(1, Date.valueOf("2020-1-1"), 1, false, false);
        List<PreReservation> preReservationListExpired = new ArrayList<>();
        preReservationListExpired.add(preReservation);

        //WHEN
        when(preReservationDao.findAllByNotExpired()).thenReturn(preReservationListExpired);
        List<PreReservation> preReservationList = preReservationController.getExpiredMailSendPreReservation();

        //THEN
        assertThat(preReservationList).isEqualTo(preReservationListExpired);

    }

    @Test
    public void getExpiredMailSendPreReservation_should_return_list_null(){

        //GIVEN
        List<PreReservation> preReservationListExpired = new ArrayList<>();

        //WHEN
        when(preReservationDao.findAllByNotExpired()).thenReturn(preReservationListExpired);
        List<PreReservation> preReservationList = preReservationController.getExpiredMailSendPreReservation();

        //THEN
        assertThat(preReservationList).isEqualTo(preReservationListExpired);

    }


    //DUMMY------------------------------------------------

    @NotNull
    private List<PreReservationDto> getPreReservationDtosDummy() {
        PreReservationDto preReservationDto1 = new PreReservationDto(1, 1, Date.from(Instant.now()), 1, false, false);
        PreReservationDto preReservationDto2 = new PreReservationDto(1,1, Date.from(Instant.now()), 2, false, false);
        List<PreReservationDto> preReservationListDto = new ArrayList<PreReservationDto>();
        preReservationListDto.add(preReservationDto1);
        preReservationListDto.add(preReservationDto2);
        return preReservationListDto;
    }

    @NotNull
    private List<PreReservation> getPreReservationsDummy() {
        PreReservation preReservation1 = new PreReservation(1, Date.from(Instant.now()), 1, false, false);
        PreReservation preReservation2 = new PreReservation(1, Date.from(Instant.now()), 2, false, false);
        List<PreReservation> preReservationList = new ArrayList<PreReservation>();
        preReservationList.add(preReservation1);
        preReservationList.add(preReservation2);
        return preReservationList;
    }
}
