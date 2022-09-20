package br.com.empresa.bo;

import java.util.List;
import java.util.Map;

import br.com.empresa.dao.PessoaDAO;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.validator.CNPJValidator;
import br.com.empresa.validator.CPFValidator;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.PessoaVO;

public class PessoaBO implements IPessoaBO {
	
	private PessoaDAO pessoaDAO;	
	
	public PessoaBO() {
		pessoaDAO = new PessoaDAO();
	}

	@Override
	public List<PessoaVO> listarPessoas(String tipoPessoa, String nomePessoa, String cpfCnpj, String cidade,
			String estado, ClienteVO cliente) throws BOValidationException, BOException {
		
		if(cliente == null || cliente.getId() == null) {
			throw new BOException(); 
		}
		
		if(cpfCnpj != null && cpfCnpj.trim().length() > 1) {
			
			if(tipoPessoa.equals("F")) {
				
				CPFValidator cpfValidator = new CPFValidator();
				try {
					cpfValidator.validate(cpfCnpj);
				} catch (Exception e) {
					throw new BOValidationException("CPF: erro de validação. "
							+ "O CPF informado está incorreto.");
				}
				
			}else if(tipoPessoa.equals("J") && 
					cpfCnpj.replaceAll("\\.", "").replaceAll("/", "").replaceAll("-", "")
					.length() > 0) {
				CNPJValidator cnpjValidator = new CNPJValidator();
				try {
					cnpjValidator.validate(cnpjValidator);
				} catch (Exception e) {
					throw new BOValidationException("CNPJ: erro de validação. "
							+ "O CNPJ informado está incorreto.");
				}
			}			
		}
		
		return pessoaDAO.listarPessoas(tipoPessoa, nomePessoa, cpfCnpj, 
				cidade, estado, cliente);
	}

	@Override
	public void salvarPessoa(PessoaVO pessoa) throws BOValidationException, BOException {
		
		if(pessoa == null) {
			throw new BOException("Não é possível salvar a pessoa pois o objeto é nulo.");
		}else if(pessoa.getDescri() == null || pessoa.getDescri().trim().length() == 0) {
			throw new BOValidationException("Nome: erro de validação. "
					+ "O nome deve ser preenchido.");
		}else if(pessoa.getCepend() == null) {
			throw new BOValidationException("CEP: erro de validação. "
					+ "O CEP deve ser preenchido.");
		}else if(pessoa.getRuaend() == null || pessoa.getRuaend().trim().length() == 0) {
			throw new BOValidationException("Rua: erro de validação. "
					+ "O nome da rua deve ser preenchido.");
		}else if(pessoa.getNumend() == null || pessoa.getNumend().trim().length() == 0) {
			throw new BOValidationException("Número: erro de validação. "
					+ "O número da residência deve ser preenchido.");
		}else if(pessoa.getBaiend() == null || pessoa.getBaiend().trim().length() == 0) {
			throw new BOValidationException("Bairro: erro de validação. "
					+ "O nome do bairro deve ser preenchido.");
		}else if(pessoa.getCidade() == null || pessoa.getCidade().trim().length() == 0) {
			throw new BOValidationException("Cidade: erro de validação. "
					+ "O nome da cidade deve ser preenchido.");
		}else if(pessoa.getEstado() == null || pessoa.getEstado().trim().length() == 0) {
			throw new BOValidationException("Estado: erro de validação. "
					+ "O estado deve ser selecionado.");
		}else if(pessoa.getCpfcnpj() == null) {
			throw new BOValidationException("CPF/CNPJ: erro de validação. "
					+ "O documento deve ser preenchido.");
		}else if(pessoa.getTippes() == null) {
			throw new BOException("Ocorreu um erro ao salver o campo tipo de pessoa."
					+ " O valor deveria ter sido informado.");
		}else {
			
			if(pessoa.getTippes().equals("F")) {
				CPFValidator cpfValidator = new CPFValidator();
				try {
					cpfValidator.validate(pessoa.getCpfcnpj());
				} catch (Exception e) {
					throw new BOValidationException("CPF: erro de validação. " +
						"O CPF informado é inválido.");
					
				}
			}else if(pessoa.getTippes().equals("J")) {
				CNPJValidator cnpjValidator = new CNPJValidator();
				try {
					cnpjValidator.validate(pessoa.getCpfcnpj());
				} catch (Exception e) {
					throw new BOValidationException("CNPJ: erro de validação. " +
							"O CNPJ informado é inválido.");
				}
			}
			
		}
		
		pessoaDAO.salvarPessoa(pessoa);
		
	}

	@Override
	public void excluirPessoa(PessoaVO pessoa) throws BOValidationException, BOException {
		
		if(pessoa == null || pessoa.getId() == null) {
			throw new BOException("Ocorreu um erro ao excluir a pessoa.");
		}
		
		pessoaDAO.excluirPessoa(pessoa);
		
	}

	@Override
	public PessoaVO buscarPessoaPorId(PessoaVO pessoa) throws BOException {
		
		if(pessoa == null || pessoa.getId() == null) {
			throw new BOException("Ocorreu um erro ao buscar a pessoa pelo ID.");
		}
		
		return pessoaDAO.buscarPessoaPorId(pessoa);
	}

	@Override
	public List<PessoaVO> listarPessoas(int first, int pageSize, Map<String, Object> filters, ClienteVO cliente)
			throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

}
