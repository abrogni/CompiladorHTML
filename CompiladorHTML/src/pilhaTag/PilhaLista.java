package pilhaTag;

public class PilhaLista<T> implements Pilha <T>{
	private ListaEncadeada<T> lista;

	public PilhaLista(){
		lista = new ListaEncadeada<>();
	}
	
	@Override
	public void push(T valor) {
		lista.inserir(valor);
	}
	
	@Override
	public T peek() {
		if(estaVazia()) {
			throw new PilhaVaziaException("Pilha esta vazia");
		}
		return lista.getPrimeiro().getInfo();
	}

	@Override
	public T pop() {
		T valor = peek();
		lista.retirar(valor);
		return valor;
	}

	@Override
	public boolean estaVazia() {
		return lista.estaVazia();
	}

	@Override
	public void liberar() {
		lista = new ListaEncadeada<>();
	}
	
	@Override
	public String toString() {
		return lista.toString();
	}
}
