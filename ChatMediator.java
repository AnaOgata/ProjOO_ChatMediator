import java.util.ArrayList;
import java.util.List;

// ----- INTERFACE MEDIATOR -----
// Define o contrato do mediador: registrar usuários e enviar mensagens
  interface Mediator {
    void registrarUsuario(Usuario usuario);
    void enviarMensagem(String mensagem, Usuario remetente);
}

// ----- MEDIATOR CONCRETO -----
// Centraliza toda a comunicação entre os usuários. Nenhum usuário conhece os outros, só conhece o mediador.
class SalaDeChat implements Mediator {

  private final String nomeSala;
  private final List<Usuario> usuarios = new ArrayList<>();

  public SalaDeChat(String nomeSala) {
  this.nomeSala = nomeSala;
}

@Override
public void registrarUsuario(Usuario usuario) {
  usuarios.add(usuario);
  System.out.println("[" + nomeSala + "] " + usuario.getNome() + " entrou na sala.");
}

@Override
public void enviarMensagem(String mensagem, Usuario remetente) {
  System.out.println("\n[" + nomeSala + "] " + remetente.getNome() + ": " + mensagem);
    for (Usuario usuario : usuarios) {
      // O mediador distribui a mensagem para todos, exceto o remetente
      if (!usuario.equals(remetente)) {
        usuario.receberMensagem(mensagem, remetente.getNome());
      }
    }
  }
}

// ----- CLASSE ABSTRATA -------
// Representa qualquer participante do chat. Conhece apenas o mediador, nunca os outros usuários diretamente.
abstract class Usuario {

  protected final String nome;
  protected final Mediator mediador;

  public Usuario(String nome, Mediator mediador) {
    this.nome = nome;
    this.mediador = mediador;
  }

  public String getNome() {
  return nome;
  }

  // Envia mensagem através do mediador
  public void enviar(String mensagem) {
  mediador.enviarMensagem(mensagem, this);
  }

  // Recebe mensagem entregue pelo mediador
  public abstract void receberMensagem(String mensagem, String nomeRemetente);
  }

  // ----- COLEGA CONCRETO: UsuarioComum -----
  class UsuarioComum extends Usuario {

  public UsuarioComum(String nome, Mediator mediador) {
  super(nome, mediador);
  mediador.registrarUsuario(this); // se registra ao ser criado
  }

  @Override
  public void receberMensagem(String mensagem, String nomeRemetente) {
  System.out.println(" >> " + nome + " recebeu de " + nomeRemetente + ": \"" + mensagem + "\"");
  }
}

  // ----- CLIENTE / DEMONSTRAÇÃO -----
  public class ChatMediator {

  public static void main(String[] args) {

  System.out.println("=== Chat com Padrão Mediator ===\n");

  // Criação do mediador (a sala de chat)
  Mediator sala = new SalaDeChat("Sala Geral");

  // Criação dos participantes — cada um se registra automaticamente na sala
  Usuario alice = new UsuarioComum("Alice", sala);
  Usuario bob = new UsuarioComum("Pedro", sala);
  Usuario carlos = new UsuarioComum("Carlos", sala);

  System.out.println();

  // Alice envia uma mensagem para todos na sala
  alice.enviar("Olá pessoal, tudo bem?");

  // Pedro responde
  pedro.enviar("Oi Alice! Tudo ótimo por aqui.");

  // Carlos participa
  carlos.enviar("Boa tarde a todos!");

  // Pedro manda mais uma
  pedro.enviar("Carlos, seja bem-vindo!");

  System.out.println("\n=== Fim da sessão ===");
  }
}
