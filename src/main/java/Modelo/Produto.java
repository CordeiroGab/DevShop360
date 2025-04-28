package Modelo;

public class Produto {
    private int idProduto;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;
    private int idFornecedor;
    private String nomeFornecedor; // Novo campo para armazenar o nome do fornecedor

    // Construtor com nome do fornecedor
    public Produto(int idProduto, String nome, String descricao, double preco, int quantidade, int idFornecedor, String nomeFornecedor) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.idFornecedor = idFornecedor;
        this.nomeFornecedor = nomeFornecedor;  // Definindo o nome do fornecedor
    }

    // Construtor sem o nome do fornecedor
    public Produto(int idProduto, String nome, String descricao, double preco, int quantidade, int idFornecedor) {
        this(idProduto, nome, descricao, preco, quantidade, idFornecedor, ""); // Nome fornecedor como vazio
    }

    // Getters e Setters
    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "idProduto=" + idProduto +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", idFornecedor=" + idFornecedor +
                ", nomeFornecedor='" + nomeFornecedor + '\'' +
                '}';
    }
}
