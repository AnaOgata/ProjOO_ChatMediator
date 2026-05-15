import java.util.ArrayList;
import java.util.List;

// ----- INTERFACE MEDIATOR -----
// Define o contrato do mediador
interface Mediator {
    void registrarUsuario(Usuario usuario);
    void enviarMensagem(String mensagem, Usuario remetente);
}

// ----- MEDIATOR CONCRETO -----
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
            // Envia para todos, exceto o remetente
            if (!usuario.equals(remetente)) {
                usuario.receberMensagem(mensagem, remetente.getNome());
            }
        }
    }
}

// ----- CLASSE ABSTRATA -----
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

    public void enviar(String mensagem) {
        mediador.enviarMensagem(mensagem, this);
    }

    public abstract void receberMensagem(String mensagem, String nomeRemetente);
}

// ----- COLEGA CONCRETO -----
class UsuarioComum extends Usuario {

    public UsuarioComum(String nome, Mediator mediador) {
        super(nome, mediador);
        mediador.registrarUsuario(this);
    }

    @Override
    public void receberMensagem(String mensagem, String nomeRemetente) {
        System.out.println(" >> " + nome + " recebeu de " + nomeRemetente + ": \"" + mensagem + "\"");
    }
}

// ----- CLIENTE / MAIN -----
public class ChatMediator {

    public static void main(String[] args) {

        System.out.println("=== Chat com Padrão Mediator ===\n");

        Mediator sala = new SalaDeChat("Sala Geral");

        Usuario alice = new UsuarioComum("Alice", sala);
        Usuario pedro = new UsuarioComum("Pedro", sala);
        Usuario carlos = new UsuarioComum("Carlos", sala);

        System.out.println();

        alice.enviar("Olá pessoal, tudo bem?");
        pedro.enviar("Oi Alice! Tudo ótimo por aqui.");
        carlos.enviar("Boa tarde a todos!");
        pedro.enviar("Carlos, seja bem-vindo!");

        System.out.println("\n=== Fim da sessão ===");
    }
}
