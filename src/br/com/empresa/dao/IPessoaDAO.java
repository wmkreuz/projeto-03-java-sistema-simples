package br.com.empresa.dao;

import java.util.List;
import java.util.Map;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.PessoaVO;

public interface IPessoaDAO {

	    public abstract List<PessoaVO> listarPessoas(String tipoPessoa, String nomePessoa,
			   String cpfCnpj, String cidade, String estado, ClienteVO cliente)
		       throws BOValidationException, BOException;
		
		public abstract void salvarPessoa(PessoaVO pessoa) throws BOValidationException, BOException;
		
		public abstract void excluirPessoa(PessoaVO pessoa) throws BOValidationException, BOException;
		
		public abstract PessoaVO buscarPessoaPorId(PessoaVO pessoa) throws BOException;
		
		public abstract List<PessoaVO> listarPessoas(int first, int pageSize,
		       Map<String, Object> filters, ClienteVO cliente) throws BOException;
}
