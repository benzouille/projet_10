package fr.biblioc.bibliocreservation.web.controller;


import com.netflix.discovery.converters.Auto;
import fr.biblioc.bibliocreservation.dao.PreReservationDao;
import fr.biblioc.bibliocreservation.dto.PreReservationDto;
import fr.biblioc.bibliocreservation.mapper.PreReservationMapper;
import fr.biblioc.bibliocreservation.model.PreReservation;
import javassist.tools.rmi.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PrereservationControllerTest {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Spy
    @InjectMocks
            //@Autowired
    PreReservationController preReservationController;

    @Mock
    PreReservationDao preReservationDao;

    @Mock
    PreReservationMapper preReservationMapper;

    @Test
    public void listeDesPreReservations_should_return_a_list() throws ObjectNotFoundException {
        //GIVEN
        preReservationController = mock(PreReservationController.class);
        preReservationController.setPreReservationDao(preReservationDao);
        //preReservationController.setPreReservationMapper(preReservationMapper);

        PreReservation preReservation1 = new PreReservation(1, Date.from(Instant.now()), 1, false, false);
        PreReservation preReservation2 = new PreReservation(1, Date.from(Instant.now()), 2, false, false);
        List<PreReservation> preReservationList = new ArrayList<>();
        preReservationList.add(preReservation1);
        preReservationList.add(preReservation2);

        PreReservationDto preReservationDto1 = new PreReservationDto(1, 1, Date.from(Instant.now()), 1, false, false);
        PreReservationDto preReservationDto2 = new PreReservationDto(1,1, Date.from(Instant.now()), 2, false, false);
        List<PreReservationDto> preReservationListDto = new ArrayList<>();
        preReservationListDto.add(preReservationDto1);
        preReservationListDto.add(preReservationDto2);

        System.out.println("preReservationList : " + preReservationList);

        when(preReservationController.getPrereservationDao()).thenReturn(preReservationList);
        when(preReservationController.getPreReservationDtos(preReservationList)).thenReturn(preReservationListDto);
        //WHEN
        //List<PreReservationDto> preReservationDtoList = preReservationController.listeDesPreReservations();
        //System.out.println("preReservationDtoList : " + preReservationDtoList);
        //THEN
        assertThat(preReservationController.listeDesPreReservations().get(1).getId_compte()).isEqualTo(1);
        //assertThat(preReservationDtoList.get(1).getId_compte()).isEqualTo(1);
    }

    @Test
    public void listPreReservationsByIdUser_should_return_a_list(){}

    @Test
    public void addPreReservation_should_add_a_list(){}

    @Test
    public void getPreReservationDtos_should_be_empty() throws ObjectNotFoundException {
        List<PreReservation> vide = new ArrayList<>();

        List<PreReservationDto> videDto = preReservationController.getPreReservationDtos(vide);

        assertThatExceptionOfType(ObjectNotFoundException.class);
    }

    @Test
    public void getPreReservationDtos_should_transform_a_list_PreReservation_To_PreReservationDto(){
    }

}
