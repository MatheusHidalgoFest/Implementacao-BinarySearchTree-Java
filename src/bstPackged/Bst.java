package bstPackged;
import java.util.Queue;
import java.util.LinkedList;

public class Bst {
    public Node raiz;
    public void excluirArvore(){
        this.raiz = null;
    }
    public void inserir(int elemento) {
        raiz = inserirRec(raiz, null, elemento);
    }
    private Node inserirRec(Node atual, Node pai, int elemento){
        if(atual == null){
            Node novoNode = new Node();
            novoNode.setData(elemento);
            novoNode.setParent(pai);
            return novoNode;
        }
        
        if (elemento <= atual.getData()) {
            atual.setLeft(inserirRec(atual.getLeft(), atual, elemento));
        } else {
            atual.setRight(inserirRec(atual.getRight(), atual, elemento));
        }

    return atual;
    }
    public Node getMinimo()  {
        return getMinimo(this.raiz);
    }
    
    private Node getMinimo(Node raiz) {
        if (raiz.getLeft() == null)
            return raiz;
        else {
            return getMinimo(raiz.getLeft());
        }
    }

    public Node getMaximo() {
        return getMaximo(this.raiz);
    }
    
    private Node getMaximo(Node raiz) {
        if (raiz.getRight() == null)
            return raiz;
        else {
            return getMaximo(raiz.getRight());
        }
    }
    
    public void remover(int valor){
        raiz = remover(raiz, valor);
    }
    
    private Node remover(Node raiz, int valor){
        if (raiz == null)
        return null;

        if (valor < raiz.getData()) {
            raiz.setLeft(remover(raiz.getLeft(),valor));
        } else if (valor > raiz.getData()) {
            raiz.setRight(remover(raiz.getRight(), valor));
        } else {
            // nó com dois filhos
            if (raiz.getLeft() != null && raiz.getRight() != null) {
                Node noMinimoDireita = getMinimo(raiz.getRight());
                raiz.setData(noMinimoDireita.getData());
                raiz.setRight(remover(raiz.getRight(), noMinimoDireita.getData()));
            }
            // nó com apenas filho à esquerda
            else if (raiz.getLeft() != null) {
                raiz = raiz.getLeft();
            }
            // nó com apenas filho à direita
            else if (raiz.getRight() != null) {
                raiz = raiz.getRight();
            }
            // nó folha
            else {
                raiz = null;
            }
        }

        return raiz;
        }

        public Node buscar(int elemento){
            return buscar(raiz, elemento);
        }
        private  Node buscar(Node atual, int elemento) {
            if (atual == null) {
                return null;
            }
    
            if (elemento == atual.getData()) {
                return atual;
            } else if (elemento < atual.getData()) {
                return buscar(atual.getLeft(), elemento);
            } else {
                return buscar(atual.getRight(), elemento);
            }
            
        }

        public void exibirArvore() {
            exibirArvore(this.raiz, 0);
        }
    
        private void exibirArvore(Node no, int nivel) {
            if (no != null) {
                exibirArvore(no.getRight(), nivel + 1);
                for (int r = 1; r <= nivel; r++)
                    System.out.print(" - ");
                System.out.println(no.getData());
                exibirArvore(no.getLeft(), nivel + 1);
            }
        }
    

    
        public Node antecessor(int valor) {
            Node atual = buscar(valor);
            if (atual == null || atual.getLeft() == null) return null;
    
            atual = atual.getLeft();
            while (atual.getRight() != null)
                atual = atual.getRight();
            return atual;
        }
    
        public Node sucessor(int valor) {
            Node atual = buscar(valor);
            if (atual == null || atual.getRight() == null) return null;
    
            atual = atual.getRight();
            while (atual.getLeft() != null)
                atual = atual.getLeft();
            return atual;
        }
    
        public void emOrdem() {
            emOrdem(raiz);
        }
    
        private void emOrdem(Node no) {
            if (no != null) {
                emOrdem(no.getLeft());
                System.out.print(no.getData() + " ");
                emOrdem(no.getRight());
            }
        }
    
        public void preOrdem() {
            preOrdem(raiz);
        }
    
        private void preOrdem(Node no) {
            if (no != null) {
                System.out.print(no.getData() + " ");
                preOrdem(no.getLeft());
                preOrdem(no.getRight());
            }
        }
    
        public void posOrdem() {
            posOrdem(raiz);
        }
    
        private void posOrdem(Node no) {
            if (no != null) {
                posOrdem(no.getLeft());
                posOrdem(no.getRight());
                System.out.print(no.getData() + " ");
            }
        }
    
        public void emOrdemReversa() {
            emOrdemReversa(raiz);
        }
    
        private void emOrdemReversa(Node no) {
            if (no != null) {
                emOrdemReversa(no.getRight());
                System.out.print(no.getData() + " ");
                emOrdemReversa(no.getLeft());
            }
        }
    
        public int quantidadeDeNos() {
            return quantidadeDeNos(raiz);
        }
    
        private int quantidadeDeNos(Node no) {
            if (no == null) return 0;
            return 1 + quantidadeDeNos(no.getLeft()) + quantidadeDeNos(no.getRight());
        }
    
        public String emLargura() {
            StringBuilder emLargura = new StringBuilder();
            if (raiz == null) return "";
    
            Queue<Node> fila = new LinkedList<>();
            fila.add(raiz);
    
            while (!fila.isEmpty()) {
                Node atual = fila.poll();
                emLargura.append(atual.getData()).append(" ");
    
                if (atual.getLeft() != null) fila.add(atual.getLeft());
                if (atual.getRight() != null) fila.add(atual.getRight());
            }
    
            return emLargura.toString();
        }
    
        public void ancestrais(int valor) {
            mostrarAncestrais(raiz, valor);
        }
    
        private boolean mostrarAncestrais(Node atual, int valor) {
            if (atual == null) return false;
    
            if (atual.getData() == valor) return true;
    
            if (mostrarAncestrais(atual.getLeft(), valor) || mostrarAncestrais(atual.getRight(), valor)) {
                System.out.print(atual.getData() + " ");
                return true;
            }
            return false;
        }
    }

