INSERT INTO `user` (`dtype`, `id`, `username`, `email`, `password`, `role`, `nama`, `alamat`, `is_pasien`, `jabatan`)
VALUES
('Petugas', 1, 'petugas', 'petugas@example.com', 'petugas', 'petugas', 'petugas', NULL, NULL, "Admin"),
('Pasien', 2, 'pasien', 'pasien@example.com', 'pasien', 'user', 'pasien', "Bandung", TRUE, NULL),
('Pasien', 3, 'Teo', 'teo@example.com', 'pasien', 'user', 'Theodora', "Jambi", TRUE, NULL),
('Pasien', 4, 'Joko', 'Joko@example.com', 'pasien', 'user', 'Joko', "Solo", TRUE, NULL)
ON DUPLICATE KEY UPDATE
`username` = VALUES(`username`),
`email` = VALUES(`email`),
`password` = VALUES(`password`),
`role` = VALUES(`role`),
`nama` = VALUES(`nama`),
`alamat` = VALUES(`alamat`),
`is_pasien` = VALUES(`is_pasien`),
`jabatan` = VALUES(`jabatan`);

INSERT INTO `paketmcu` (`id`, `deskripsi`, `harga`, `jenis`, `kategori`, `nama`) VALUES
(1, '<p>Pemeriksaan Thorax untuk memeriksa kondisi paru-paru dan jantung.</p><ul><li>Rontgen Thorax</li><li>CT Scan</li><li>Konsul Dokter Spesialis Paru (Sp.P)</li></ul>', 350000, 'thorax', 'thorax', 'Thorax 1'),
(2, '<p>Paket pemeriksaan thorax ini dirancang untuk mendeteksi kondisi kesehatan pada area dada, termasuk organ-organ penting seperti paru-paru, jantung, dan saluran pernapasan. Pemeriksaan thorax dapat membantu dalam mendiagnosis berbagai masalah pernapasan atau jantung, seperti infeksi paru-paru, tuberkulosis, dan gangguan jantung. Paket ini mencakup serangkaian tes untuk memeriksa kondisi medis terkait thorax secara komprehensif dan tepat.</p><ol><li>Rontgen Dada (X-Ray) – Pemeriksaan radiologi untuk melihat gambaran umum struktur dada, termasuk paru-paru, jantung, dan tulang rusuk.</li><li>Tes Fungsi Paru – Mengukur kapasitas dan kinerja paru-paru untuk memastikan fungsinya optimal.</li><li>Elektrokardiogram (EKG) – Pemeriksaan jantung untuk mendeteksi adanya kelainan irama jantung atau gangguan jantung lainnya.</li><li>Tes Darah – Untuk mendeteksi infeksi atau masalah lain yang mungkin memengaruhi fungsi paru-paru dan jantung.</li></ol>', 100000, 'thorax', 'thorax', 'Thorax 2'),
(3, '<p>Paket pemeriksaan abdomen untuk mendeteksi gangguan pada organ-organ dalam perut, seperti hati, ginjal, pankreas, dan saluran pencernaan.</p><ul><li>USG Abdomen</li><li>Tes Darah Lengkap</li><li>Konsultasi Dokter Spesialis Penyakit Dalam</li></ul>', 400000, 'abdomen', 'abdomen', 'Abdomen 1'),
(4, '<p>Pemeriksaan abdomen yang lebih lengkap untuk mendeteksi kelainan pada organ dalam perut.</p><ul><li>USG Abdomen Lengkap</li><li>CT Scan Abdomen</li><li>Tes Fungsi Hati</li><li>Konsultasi Spesialis Gastroenterologi</li></ul>', 500000, 'abdomen', 'abdomen', 'Abdomen 2'),
(5, '<p>Paket pemeriksaan dasar untuk mendeteksi diabetes.</p><ul><li>Gula Darah Puasa</li><li>Gula Darah Sewaktu</li><li>HbA1c</li></ul>', 200000, 'diabetes', 'diabetes', 'Diabetes 1'),
(6, '<p>Pemeriksaan diabetes komprehensif untuk memantau kondisi gula darah dan komplikasinya.</p><ul><li>Gula Darah Puasa & 2 Jam Setelah Makan</li><li>HbA1c</li><li>Tes Fungsi Ginjal</li><li>Konsultasi Dokter Spesialis Endokrinologi</li></ul>', 300000, 'diabetes', 'diabetes', 'Diabetes 2'),
(7, '<p>Paket pemeriksaan awal untuk mendeteksi tuberkulosis.</p><ul><li>Rontgen Dada</li><li>Tes Dahak (BTA)</li><li>Tes Darah Lengkap</li></ul>', 250000, 'tbc', 'tbc', 'TBC 1'),
(8, '<p>Paket pemeriksaan lanjutan untuk tuberkulosis dengan metode diagnostik lebih lengkap.</p><ul><li>Rontgen Thorax</li><li>Tes Dahak (BTA & PCR)</li><li>Tes Mantoux</li><li>Konsultasi Dokter Spesialis Paru</li></ul>', 350000, 'tbc', 'tbc', 'TBC 2'),
(9, '<p>Paket pemeriksaan dasar untuk mendeteksi skoliosis atau kelainan tulang belakang.</p><ul><li>Rontgen Tulang Belakang</li><li>Konsultasi Dokter Spesialis Ortopedi</li></ul>', 300000, 'skoliosis', 'skoliosis', 'Skoliosis 1'),
(10, '<p>Paket pemeriksaan skoliosis yang lebih lengkap dengan analisis mendalam.</p><ul><li>Rontgen Tulang Belakang Lengkap</li><li>MRI Tulang Belakang</li><li>Tes Kepadatan Tulang</li><li>Konsultasi Spesialis Rehabilitasi Medis</li></ul>', 450000, 'skoliosis', 'skoliosis', 'Skoliosis 2')
ON DUPLICATE KEY UPDATE
    `deskripsi` = VALUES(`deskripsi`),
    `harga` = VALUES(`harga`),
    `jenis` = VALUES(`jenis`),
    `kategori` = VALUES(`kategori`),
    `nama` = VALUES(`nama`);

INSERT INTO `pendaftaranmcu` (`id`, `booking`, `status`, `paketmcu_id`, `pasien_id`) VALUES
(1, '2025-01-10', 'complete', 1, 2),
(2, '2025-01-11', 'pending', 2, 3),
(3, '2025-01-12', 'complete', 3, 4)
ON DUPLICATE KEY UPDATE
`booking` = VALUES(`booking`),
`status` = VALUES(`status`),
`paketmcu_id` = VALUES(`paketmcu_id`),
`pasien_id` = VALUES(`pasien_id`);

