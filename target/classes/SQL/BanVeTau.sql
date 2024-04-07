CREATE DATABASE BanVeTau;
use BanVeTau

CREATE TABLE NhaGa (
    MaNhaGa NVARCHAR(3) PRIMARY KEY,
    TenNhaGa NVARCHAR(100) NOT NULL
);
GO

CREATE TABLE KhachHang (
    MaKH NVARCHAR(8) PRIMARY KEY,
    HoTen NVARCHAR(100) NOT NULL,
    CCCD NVARCHAR(12) NOT NULL,
    GioiTinh NVARCHAR(3) NOT NULL,
    SDT NVARCHAR(10) NOT NULL,
    Email NVARCHAR(50) NOT NULL
);
GO

CREATE TABLE NhanVien (
    MaNV NVARCHAR(8) PRIMARY KEY,
    HoTen NVARCHAR(100) NOT NULL,
    CCCD NVARCHAR(12) NOT NULL,
    GioiTinh NVARCHAR(3) NOT NULL,
    SDT NVARCHAR(10) NOT NULL,
    Email NVARCHAR(50) NOT NULL,
    NgaySinh DATE NOT NULL,
    TrinhDo NVARCHAR(100) NOT NULL,
    MaNhaGa NVARCHAR(3),
    FOREIGN KEY (MaNhaGa) REFERENCES NhaGa(MaNhaGa)
);
GO

CREATE TABLE Tau (
    MaTau NVARCHAR(10) PRIMARY KEY,
    LoaiTau NVARCHAR(50) NOT NULL,
    MaNhaGa NVARCHAR(3),
    FOREIGN KEY (MaNhaGa) REFERENCES NhaGa(MaNhaGa)
);
GO

CREATE TABLE ChuyenTau (
    MaChuyenTau NVARCHAR(10) PRIMARY KEY,
    MaTau NVARCHAR(10),
    GaDi NVARCHAR(100) NOT NULL,
    GaDen NVARCHAR(100) NOT NULL,
    GioDi DATETIME NOT NULL,
    GioDen DATETIME NOT NULL,
    FOREIGN KEY (MaTau) REFERENCES Tau(MaTau)
);

GO

CREATE TABLE Ve (
    MaVe NVARCHAR(10) PRIMARY KEY,
    TenVe NVARCHAR(100) NOT NULL,
    LoaiVe NVARCHAR(10) NOT NULL,
    NgayDi DATETIME NOT NULL,
    NgayVe DATETIME NOT NULL,
    MaKH NVARCHAR(8),
    MaNV NVARCHAR(8),
    MaChuyenTau NVARCHAR(10),
    FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH),
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
    FOREIGN KEY (MaChuyenTau) REFERENCES ChuyenTau(MaChuyenTau)
);
GO

CREATE TABLE Toa (
    MaTau NVARCHAR(10),
    SoToaTau NVARCHAR(10) PRIMARY KEY,
    LoaiTau NVARCHAR(100),
    SoPhong INT,
    MaGhe NVARCHAR(10),
	SoGhe int,
    FOREIGN KEY (MaTau) REFERENCES Tau(MaTau)
);
GO


--Dữ liệu mẫu cho bảng NhaGa
INSERT INTO NhaGa (MaNhaGa, TenNhaGa) VALUES
    (N'NGA', N'Nhà ga A'),
    (N'NGB', N'Nhà ga B'),
    (N'NGC', N'Nhà ga C'),
    (N'NGD', N'Nhà ga D'),
    (N'NGE', N'Nhà ga E'),
    (N'NGF', N'Nhà ga F'),
    (N'NGG', N'Nhà ga G'),
    (N'NGH', N'Nhà ga H'),
    (N'NGI', N'Nhà ga I'),
    (N'NGK', N'Nhà ga K');

SELECT * FROM [dbo].[NhaGa];

--Dữ liệu mẫu cho bảng KhachHang
INSERT INTO KhachHang (MaKH, HoTen, CCCD, GioiTinh, SDT, Email) VALUES 
	('KH001', N'Nguyễn Văn A', '012345678901', N'Nam', '0123456789', N'nva@gmail.com'),
	('KH002', N'Lê Thị B', '012345678902', N'Nữ', '0123456790', N'ltb@gmail.com'),
	('KH003', N'Trần Văn C', '012345678903', N'Nam', '0123456798', N'tvc@gmail.com'),
	('KH004', N'Phạm Thị D', '012345678904', N'Nữ', '0123456791', N'ptd@gmail.com'),
	('KH005', N'Hoàng Văn E', '012345678905', N'Nam', '0123456797', N'hve@gmail.com'),
	('KH006', N'Vũ Thị F', '012345678906', N'Nữ', '0123456792', N'vtf@gmail.com'),
	('KH007', N'Đặng Văn G', '012345678907', N'Nam', '0123456793', N'dvg@gmail.com'),
	('KH008', N'Trương Thị H', '012345678908', N'Nữ', '0123456794', N'tth@gmail.com'),
	('KH009', N'Lý Văn I', '012345678909', N'Nam', '0123456795', N'lvi@gmail.com'),
	('KH010', N'Nguyễn Thị K', '012345678910', N'Nữ', '0123456796', N'ntk@gmail.com');


SELECT * FROM [dbo].[KhachHang];

--Dữ liệu mẫu cho bảng NhanVien
INSERT INTO NhanVien (MaNV, HoTen, CCCD, GioiTinh, SDT, Email, NgaySinh, TrinhDo, MaNhaGa) VALUES 
	('NV001', N'Nguyễn Văn A', '012345678901', N'Nam', '0123456789', 'nva@gmail.com', '1990-01-01', N'Cử nhân', N'NGA'),
    ('NV002', N'Lê Thị B', '012345678902', N'Nữ', '0123456790', 'ltb@gmail.com', '1995-02-02', N'Cao đẳng', N'NGA'),
    ('NV003', N'Trần Văn C', '012345678903', N'Nam', '0123456798', 'tvc@gmail.com', '1992-03-03', N'Đại học', N'NGA'),
    ('NV004', N'Phạm Thị D', '012345678904', N'Nữ', '0123456791', 'ptd@gmail.com', '1997-04-04', N'Trung cấp', N'NGA'),
    ('NV005', N'Hoàng Văn E', '012345678905', N'Nam', '0123456797', 'hve@gmail.com', '1994-05-05', N'Đại học', N'NGA'),
    ('NV006', N'Nguyễn Thị F', '098345678906', N'Nữ', '0901234567', 'ntf@gmail.com', '1991-06-06', N'Cao đẳng', N'NGA'),
    ('NV007', N'Lê Văn G', '098345678907', N'Nam', '0123456890', 'lvg@gmail.com', '1998-07-07', N'Trung cấp', N'NGA'),
    ('NV008', N'Trần Thị H', '098345678908', N'Nữ', '0235678901', 'tth@gmail.com', '1993-08-08', N'Cử nhân', N'NGA'),
    ('NV009', N'Phạm Văn I', '098345678909', N'Nam', '0347890123', 'pvi@gmail.com', '1996-09-09', N'Đại học', N'NGA'),
    ('NV010', N'Hoàng Thị K', '098345678910', N'Nữ', '0889012345', 'htk@gmail.com', '1999-10-10', N'Cao đẳng', N'NGA');

SELECT * FROM [dbo].[NhanVien];

--Dữ liệu mẫu cho bảng Tau
INSERT INTO Tau (MaTau, LoaiTau, MaNhaGa) VALUES 
	(N'TT001', N'Tàu hỏa', N'NGA'),
    (N'TT002', N'Tàu hỏa', N'NGB'),
    (N'TT003', N'Tàu cao tốc', N'NGC'),
    (N'TT004', N'Tàu cao tốc', N'NGD'),
    (N'TT005', N'Tàu hỏa', N'NGE'),
    (N'TT006', N'Tàu cao tốc', N'NGF'),
    (N'TT007', N'Tàu hỏa', N'NGG'),
    (N'TT008', N'Tàu cao tốc', N'NGH'),
    (N'TT009', N'Tàu hỏa', N'NGI'),
    (N'TT010', N'Tàu cao tốc', N'NGK');
SELECT * FROM Tau;

----Dữ liệu mẫu cho bảng ChuyenTau
INSERT INTO ChuyenTau (MaChuyenTau, GaDi, GaDen, GioDi, GioDen, MaTau) VALUES
    (N'CT001', N'Ga A', N'Ga B', '2024-04-01 08:00:00', '2024-04-01 10:00:00', N'TT001'),
    (N'CT002', N'Ga B', N'Ga C', '2024-04-01 10:30:00', '2024-04-01 12:30:00', N'TT002'),
    (N'CT003', N'Ga C', N'Ga D', '2024-04-01 13:00:00', '2024-04-01 15:00:00', N'TT003'),
    (N'CT004', N'Ga D', N'Ga E', '2024-04-01 15:30:00', '2024-04-01 17:30:00', N'TT004'),
    (N'CT005', N'Ga E', N'Ga F', '2024-04-01 18:00:00', '2024-04-01 20:00:00', N'TT005'),
    (N'CT006', N'Ga F', N'Ga G', '2024-04-01 20:30:00', '2024-04-01 22:30:00', N'TT006'),
    (N'CT007', N'Ga G', N'Ga H', '2024-04-01 23:00:00', '2024-04-02 01:00:00', N'TT007'),
    (N'CT008', N'Ga H', N'Ga I', '2024-04-02 01:30:00', '2024-04-02 03:30:00', N'TT008'),
    (N'CT009', N'Ga I', N'Ga K', '2024-04-02 04:00:00', '2024-04-02 06:00:00', N'TT009'),
    (N'CT010', N'Ga K', N'Ga A', '2024-04-02 06:30:00', '2024-04-02 08:30:00', N'TT010');



SELECT * FROM [dbo].[ChuyenTau];

--Dữ liệu mẫu cho bảng Ve
INSERT INTO Ve (MaVe, TenVe, LoaiVe, NgayDi, NgayVe, MaKH, MaNV, MaChuyenTau) VALUES 
    (N'V001', N'Vé 1', N'Loại 1', '2024-04-01 08:00:00', '2024-04-01 10:00:00', N'KH001', N'NV001', N'CT001'),
    (N'V002', N'Vé 2', N'Loại 2', '2024-04-01 10:30:00', '2024-04-01 12:30:00', N'KH002', N'NV002', N'CT002'),
    (N'V003', N'Vé 3', N'Loại 1', '2024-04-01 13:00:00', '2024-04-01 15:00:00', N'KH003', N'NV003', N'CT003'),
    (N'V004', N'Vé 4', N'Loại 2', '2024-04-01 15:30:00', '2024-04-01 17:30:00', N'KH004', N'NV004', N'CT004'),
    (N'V005', N'Vé 5', N'Loại 1', '2024-04-01 18:00:00', '2024-04-01 20:00:00', N'KH005', N'NV005', N'CT005'),
    (N'V006', N'Vé 6', N'Loại 2', '2024-04-01 20:30:00', '2024-04-01 22:30:00', N'KH006', N'NV006', N'CT006'),
    (N'V007', N'Vé 7', N'Loại 1', '2024-04-01 23:00:00', '2024-04-02 01:00:00', N'KH007', N'NV007', N'CT007'),
    (N'V008', N'Vé 8', N'Loại 2', '2024-04-02 01:30:00', '2024-04-02 03:30:00', N'KH008', N'NV008', N'CT008'),
    (N'V009', N'Vé 9', N'Loại 1', '2024-04-02 04:00:00', '2024-04-02 06:00:00', N'KH009', N'NV009', N'CT009'),
    (N'V010', N'Vé 10', N'Loại 2', '2024-04-02 06:30:00', '2024-04-02 08:30:00', N'KH010', N'NV010', N'CT010');

SELECT * FROM [dbo].[Ve];

--Dữ liệu mẫu cho bảng Toa
INSERT INTO Toa (MaTau, SoToaTau, LoaiTau, SoPhong, MaGhe) VALUES 
    (N'TT001', N'Toa001', N'Tàu hỏa', 10, N'G001'),
    (N'TT001', N'Toa002', N'Tàu hỏa', 12, N'G002'),
    (N'TT002', N'Toa003', N'Tàu hỏa', 8, N'G003'),
    (N'TT002', N'Toa004', N'Tàu hỏa', 10, N'G004'),
    (N'TT003', N'Toa005', N'Tàu cao tốc', 6, N'G005'),
    (N'TT003', N'Toa006', N'Tàu cao tốc', 8, N'G006'),
    (N'TT004', N'Toa007', N'Tàu cao tốc', 10, N'G007'),
    (N'TT004', N'Toa008', N'Tàu cao tốc', 12, N'G008'),
    (N'TT005', N'Toa009', N'Tàu hỏa', 8, N'G009'),
    (N'TT005', N'Toa010', N'Tàu hỏa', 10, N'G010');

SELECT * FROM [dbo].[Toa];