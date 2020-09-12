package fr.biblioc.bibliocreservation.web.controller;

import fr.biblioc.bibliocreservation.dao.PreReservationDao;
import fr.biblioc.bibliocreservation.dto.PreReservationDto;
import fr.biblioc.bibliocreservation.model.PreReservation;
import fr.biblioc.bibliocreservation.web.exceptions.FunctionalException;
import javassist.tools.rmi.ObjectNotFoundException;
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
import java.util.Date;
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

    @Test
    public void listPreReservationsByIdUser_should_return_a_list(){}

    @Test
    public void addPreReservation_should_add_a_list(){}

    @Test
    public void getPreReservationDtos_should_be_empty() throws FunctionalException {
        List<PreReservation> vide = new ArrayList<>();
        Assertions.assertThrows(FunctionalException.class, () -> preReservationController.getPreReservationDtos(vide));
    }

    @Test
    public void getPreReservationDtos_should_transform_a_list_PreReservation_To_PreReservationDto(){
    }


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
