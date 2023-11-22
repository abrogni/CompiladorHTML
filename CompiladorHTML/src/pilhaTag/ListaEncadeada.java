package pilhaTag;

public class ListaEncadeada<T> {

	private NoLista<T> primeiro;
	
	public ListaEncadeada() {
		primeiro = null;
	}
	
	public NoLista<T> getPrimeiro() {
		return primeiro;
	}
	
	public void inserir(T info) {
		NoLista<T> novo = new NoLista<>();
		novo.setInfo(info);
		novo.setProximo(getPrimeiro());
		this.primeiro = novo;
	}
	
	public boolean estaVazia() { 
		return this.primeiro == null; 
	}
	
	public NoLista<T> buscar(T info){
		NoLista<T> p = getPrimeiro();
		while (p != null) {
			if (p.getInfo().equals(info)) {
				return p;
			}
			p = p.getProximo();
		}
		return null;
	}
	
	public void retirar(T valor) {
		NoLista<T> anterior = null;
		NoLista<T> p = getPrimeiro();
		//enquanto p nao for nullo nem for igual o valor que quero retirar
		//anterior recebe p
		//p recebe o proximo no
		while((p != null) && (!p.getInfo().equals(valor))) {
			anterior = p;
			p = p.getProximo();
		}
		//se p nao for nullo
		if (p != null) {
			//se p for o primeiro
			if (p == primeiro) {
				//primeiro passa a ser o seu proximo
				primeiro = primeiro.getProximo();
			//se nao for o primeiro no o proximo do nó anterior ao que quero remover passar a ser o proximo do que quero remover
			}else {
				anterior.setProximo( p.getProximo());
			}
		}
	}
	
	public int obterComprimento() {
		int qtdNos = 0;
		NoLista<T> p = getPrimeiro();
		while (p != null) {
			qtdNos++;
			p = p.getProximo();
		}
		return qtdNos;
	}
	
	public NoLista<T> obterNo(int idx){
		if(idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		NoLista<T> p = getPrimeiro();
		while ((p != null) && (idx > 0)) {
			idx--;
			p = p.getProximo();
		}
		if(p == null) {
			throw new IndexOutOfBoundsException();
		}
		return p;
	}
	
	public String toString() {
		String resultado = "";
		NoLista<T> p = getPrimeiro();
		
		while (p != null) {
			if (p != getPrimeiro()) {
				resultado += ",";
			}
			resultado += p.getInfo();
			p = p.getProximo();
		}
		return resultado;
	}
}   
