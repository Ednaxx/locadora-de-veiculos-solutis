INSERT INTO fabricantes (id, nome) VALUES
                                       ('1e1d1a1e-1d1a-1e1d-1a1e-1d1a1e1d1a1e', 'Toyota'),
                                       ('2e2d2a2e-2d2a-2e2d-2a2e-2d2a2e2d2a2e', 'Ford');

INSERT INTO modelos_carro (id, descricao, category, fabricante_id) VALUES
                                                                       ('3e3d3a3e-3d3a-3e3d-3a3e-3d3a3e3d3a3e', 'LARGE_SEDAN', 'LARGE_SEDAN', '1e1d1a1e-1d1a-1e1d-1a1e-1d1a1e1d1a1e'),
                                                                       ('4e4d4a4e-4d4a-4e4d-4a4e-4d4a4e4d4a4e', 'LARGE_SEDAN', 'LARGE_SEDAN', '2e2d2a2e-2d2a-2e2d-2a2e-2d2a2e2d2a2e');

INSERT INTO acessorios (id, nome, descricao) VALUES
                                                 ('5e5d5a5e-5d5a-5e5d-5a5e-5d5a5e5d5a5e', 'GPS', 'Navigation system'),
                                                 ('6e6d6a6e-6d6a-6e6d-6a6e-6d6a6e6d6a6e', 'Child Seat', 'Safety seat for children');

INSERT INTO carros (id, placa, chassi, cor, valor_diaria, url_imagem, modelo_id) VALUES
                                                                                     ('7e7d7a7e-7d7a-7e7d-7a7e-7d7a7e7d7a7e', 'ABC1234', '1HGBH41JXMN109186', 'Red', 100.00, 'http://example.com/car1.jpg', '3e3d3a3e-3d3a-3e3d-3a3e-3d3a3e3d3a3e'),
                                                                                     ('8e8d8a8e-8d8a-8e8d-8a8e-8d8a8e8d8a8e', 'XYZ5678', '1HGBH41JXMN109187', 'Blue', 150.00, 'http://example.com/car2.jpg', '4e4d4a4e-4d4a-4e4d-4a4e-4d4a4e4d4a4e');

INSERT INTO carro_acessorios (carro_id, acessorio_id) VALUES
                                                          ('7e7d7a7e-7d7a-7e7d-7a7e-7d7a7e7d7a7e', '5e5d5a5e-5d5a-5e5d-5a5e-5d5a5e5d5a5e'),
                                                          ('8e8d8a8e-8d8a-8e8d-8a8e-8d8a8e8d8a8e', '6e6d6a6e-6d6a-6e6d-6a6e-6d6a6e6d6a6e');

INSERT INTO pessoas (id, nome, data_nascimento, CPF, sexo, email, tipo_pessoa, CNH) VALUES
    ('9e9d9a9e-9d9a-9e9d-9a9e-9d9a9e9d9a9e', 'John Doe', '1980-01-01', '123.456.789-00', 'MASCULINO', 'john.doe@example.com', 'motorista', '1234567890');

INSERT INTO carrinho_compras (id, motorista_id) VALUES
    ('aeaeaeae-aeae-aeae-aeae-aeaeaeaeaeae', '9e9d9a9e-9d9a-9e9d-9a9e-9d9a9e9d9a9e');

INSERT INTO carrinho_compra_carro (carrinho_id, carro_id) VALUES
    ('aeaeaeae-aeae-aeae-aeae-aeaeaeaeaeae', '7e7d7a7e-7d7a-7e7d-7a7e-7d7a7e7d7a7e');

INSERT INTO apolices_seguro (id, valor_franquia, protecao_terceiro, protecao_causas_naturais, protecao_roubo) VALUES
    ('bebebebe-bebe-bebe-bebe-bebebebebebe', 500.00, true, true, true);
--
INSERT INTO alugueis (id, data_pedido, data_devolucao, valor_total, apolice_seguro_id, carro_id, motorista_id) VALUES
    ('cececece-cece-cece-cece-cececececece', '2023-01-01', '2023-01-10', 1000.00, 'bebebebe-bebe-bebe-bebe-bebebebebebe', '7e7d7a7e-7d7a-7e7d-7a7e-7d7a7e7d7a7e', '9e9d9a9e-9d9a-9e9d-9a9e-9d9a9e9d9a9e');

INSERT INTO pagamentos (id, rental_id, metodo_de_pagamento, confirmed) VALUES
    ('dededede-dede-dede-dede-dededededede', 'cececece-cece-cece-cece-cececececece', 'CARTAO_CREDITO', true);
