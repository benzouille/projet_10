package fr.biblioc.bibliocreservation.mapper;

import fr.biblioc.bibliocreservation.dto.ListeAttenteDto;
import fr.biblioc.bibliocreservation.model.ListeAttente;
import org.mapstruct.Mapper;

/**
 * Mapper mapstruct de l'Entity au DTO et l'inverse
 */
@Mapper(componentModel = "spring")
public interface ListeAttenteMapper {
    ListeAttenteDto listeAttenteToListeAttenteDto(ListeAttente listeAttente);
        ListeAttente listeAttenteDtoToListeAttente(ListeAttenteDto listeAttenteDto);
}
