CREATE DATABASE BanVeTau1;
use BanVeTau1

CREATE TABLE NhaGa (
    MaNhaGa NVARCHAR(100) PRIMARY KEY,
    TenNhaGa NVARCHAR(100) NOT NULL,
	DiaChi NVARCHAR(100) NOT NULL
);
GO

CREATE TABLE KhachHang (
    MaKH NVARCHAR(50) PRIMARY KEY,
    HoTen NVARCHAR(100) NOT NULL,
    CCCD NVARCHAR(12) NOT NULL,
    GioiTinh NVARCHAR(3) NOT NULL,
    SDT NVARCHAR(10) NOT NULL,
    Email NVARCHAR(50) NOT NULL
);
GO

CREATE TABLE NhanVien (
    MaNV NVARCHAR(50) PRIMARY KEY,
    HoTen NVARCHAR(100) NOT NULL,
    CCCD NVARCHAR(12) NOT NULL,
    GioiTinh NVARCHAR(3) NOT NULL,
    SDT NVARCHAR(10) NOT NULL,
    Email NVARCHAR(50) NOT NULL,
    NgaySinh DATE NOT NULL,
    TrinhDo NVARCHAR(100) NOT NULL,
    MaNhaGa NVARCHAR(100),
    FOREIGN KEY (MaNhaGa) REFERENCES NhaGa(MaNhaGa)
);
GO

CREATE TABLE Tau (
    MaTau NVARCHAR(100) PRIMARY KEY,
	TenTau NVARCHAR(100),
    LoaiTau NVARCHAR(50) NOT NULL,
    MaNhaGa NVARCHAR(100),
    FOREIGN KEY (MaNhaGa) REFERENCES NhaGa(MaNhaGa),
);
GO

CREATE TABLE ChuyenTau (
    MaChuyenTau NVARCHAR(100) PRIMARY KEY,
    MaTau NVARCHAR(100),
    GaDi NVARCHAR(10),
    GaDen NVARCHAR(10),
    NgayDi DATE,
	GioDi TIME,
    NgayDen DATE,
	GioDen TIME,
    FOREIGN KEY (MaTau) REFERENCES Tau(MaTau)
);
GO

CREATE TABLE Ve (
    MaVe NVARCHAR(10) PRIMARY KEY,
	GaDi NVARCHAR(10),
	GaDen NVARCHAR(10),
	NgayDi date,
	GioDi NVARCHAR(30),
	NgayVe date,
	GioVe NVARCHAR(30),
	Toa NVARCHAR(100) NOT NULL,
    LoaiCho NVARCHAR(10) NOT NULL,
    Cho INT NOT NULL,
    GiaVe FLOAT NOT NULL,
	/*MaChuyenTau NVARCHAR(100), */
    MaKH NVARCHAR(50),
    MaNV NVARCHAR(50),
	/*FOREIGN KEY (MaChuyenTau) REFERENCES ChuyenTau(MaChuyenTau), */
    FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH),
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
);
GO

CREATE TABLE Toa (
    MaTau NVARCHAR(100),
    MaToa NVARCHAR(100) PRIMARY KEY,
	TenToa NVARCHAR(100),
    TinhTrang NVARCHAR(10),
	LoaiToa NVARCHAR(100) NOT NULL, 
    SoGhe INT,
    FOREIGN KEY (MaTau) REFERENCES Tau(MaTau)
);
GO

CREATE TABLE Ghe (
	MaGhe NVARCHAR(10),
	SoGhe INT,
	TinhTrang NVARCHAR(10),
	MaToa NVARCHAR(100),
	FOREIGN KEY (MaToa) REFERENCES Toa(MaToa)
);
GO






--Dữ liệu mẫu cho bảng NhaGa
INSERT INTO NhaGa (MaNhaGa, TenNhaGa, DiaChi) VALUES
    (N'GHN', N'Hà Nội', N'Hà Nội'),
    (N'GADV', N'An Dương Vương', N'Sài Gòn'),
    (N'GNL', N'Nghi Long', N'Nghệ An'),
    (N'GTT', N'Trản Táo', N'Đồng Nai'),
    (N'GHT', N'Hà Thanh', N'Quảng Trị'),
    (N'GST', N'Sóng Thần', N'Bình Dương'),
    (N'GTL', N'Trường Lâm', N'Thanh Hóa'),
    (N'GHD', N'Hòa Duyệt', N'Hà Tĩnh'),
    (N'GVX', N'Văn Xá', N'Thừa Thiên Huế'),
    (N'GHP', N'Hải Phòng', N'Hải Phòng');

SELECT * FROM [dbo].[NhaGa];

/*
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

SELECT * FROM [dbo].[KhachHang]; */

--Dữ liệu mẫu cho bảng NhanVien
INSERT INTO NhanVien (MaNV, HoTen, CCCD, GioiTinh, SDT, Email, NgaySinh, TrinhDo, MaNhaGa) VALUES 
	('NV01', N'Nguyễn Văn Tú', '012345678901', N'Nam', '0123456789', 'nvt@gmail.com', '1990-01-01', N'Cử nhân', N'GHN'),
    ('NV02', N'Lê Thị Nga', '012345678902', N'Nữ', '0123456790', 'ltn@gmail.com', '1995-02-02', N'Cao đẳng', N'GADV'),
    ('NV03', N'Trần Văn Long', '012345678903', N'Nam', '0123456798', 'tvl@gmail.com', '1992-03-03', N'Đại học', N'GNL'),
    ('NV04', N'Phạm Thị Thu', '012345678904', N'Nữ', '0123456791', 'ptt@gmail.com', '1997-04-04', N'Trung cấp', N'GTT'),
    ('NV05', N'Hoàng Văn Triết', '012345678905', N'Nam', '0123456797', 'hvt@gmail.com', '1994-05-05', N'Đại học', N'GHT'),
    ('NV06', N'Nguyễn Thị Thu Hà', '098345678906', N'Nữ', '0901234567', 'ntth@gmail.com', '1991-06-06', N'Cao đẳng', N'GST'),
    ('NV07', N'Lê Văn Huy', '098345678907', N'Nam', '0123456890', 'lvh@gmail.com', '1998-07-07', N'Trung cấp', N'GTL'),
    ('NV08', N'Trần Thị Linh', '098345678908', N'Nữ', '0235678901', 'ttl@gmail.com', '1993-08-08', N'Cử nhân', N'GHD'),
    ('NV09', N'Phạm Văn Khang', '098345678909', N'Nam', '0347890123'	, 'pvk@gmail.com', '1996-09-09', N'Đại học', N'GVX'),
    ('NV10', N'Cao Thị Yến', '098345678910', N'Nữ', '0889012345', 'cty@gmail.com', '1999-10-10', N'Cao đẳng', N'GHP');

SELECT * FROM [dbo].[NhanVien];

--Dữ liệu mẫu cho bảng Tau
/*INSERT INTO Tau (MaTau, LoaiTau, MaNhaGa, SoLuongToa, SoLuongGhe) VALUES 
    (N'TT001', N'Tàu hỏa', N'NGA', 7, 264),
    (N'TT002', N'Tàu hỏa', N'NGB', 7, 264),
    (N'TT003', N'Tàu cao tốc', N'NGC', 7, 264),
    (N'TT004', N'Tàu cao tốc', N'NGD', 7, 264),
    (N'TT005', N'Tàu hỏa', N'NGE', 7, 264),
    (N'TT006', N'Tàu cao tốc', N'NGF', 7, 264),
    (N'TT007', N'Tàu hỏa', N'NGG', 7, 264),
    (N'TT008', N'Tàu cao tốc', N'NGH', 7, 264),
    (N'TT009', N'Tàu hỏa', N'NGI', 7, 264),
    (N'TT010', N'Tàu cao tốc', N'NGK', 7, 264);
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
*/