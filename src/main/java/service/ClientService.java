package service;

import dto.CarDto;
import dto.ClientDto;
import model.Car;
import model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public ClientDto toDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setCpf(client.getCpf());
        clientDto.setName(client.getName());
        clientDto.setEmail(client.getEmail());
        clientDto.setTelephone(client.getTelephone());
        return clientDto;
    }

    public Client toModel(ClientDto clientDto) {
        Client client = new Client();
        client.setCpf(clientDto.getCpf());
        client.setName(clientDto.getName());
        client.setEmail(clientDto.getEmail());
        client.setTelephone(clientDto.getTelephone());
        return client;
    }

    public String saveClient(ClientDto clientDto) {
        Client saveClient = toModel(clientDto);
        clientRepository.save(saveClient);
        return "Client successfully saved";

    }

    public ClientDto searchById(Integer client_id) {
        Optional<Client> client = clientRepository.findById(client_id);
        Client dataClient;
        ClientDto clientDto = new ClientDto();

        if (client.isPresent()) {
            dataClient = client.get();
            toDto(dataClient);
        }
        return clientDto;
    }

    public String update(Integer client_id, ClientDto clientDto) {
        Optional<Client> client = clientRepository.findById(client_id);
        Client dataClient = new Client();

        if (client.isPresent()) {
            dataClient = client.get();
            if (clientDto.getName() != null) {
                dataClient.setName(clientDto.getName());
            }
            if (clientDto.getCpf() != null) {
                dataClient.setCpf(clientDto.getCpf());
            }
            if (clientDto.getEmail() != null) {
                dataClient.setEmail(clientDto.getEmail());
            }
            if (clientDto.getTelephone() != null) {
                dataClient.setTelephone(clientDto.getTelephone());
            }
            clientRepository.save(dataClient);
        }
        return "Client successfully updated";
    }

    public String delete(Integer client_id) {
        clientRepository.deleteById(client_id);
        return "Client successfully deleted";
    }

    public List<ClientDto> listAll() {
        List<Client> clientList = clientRepository.findAll();
        List<ClientDto> clientDtoList = new ArrayList<>();
        for (Client client : clientList) {
            ClientDto clientDto = new ClientDto();
            toDto(client);
            clientDtoList.add(clientDto);
        }
        return clientDtoList;
    }
}