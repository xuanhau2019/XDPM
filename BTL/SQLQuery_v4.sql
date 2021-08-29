use master
go

create database XDPM_Nhom10_ThueBangDia
go

use XDPM_Nhom10_ThueBangDia
go

create table TuaDia
(
	maTua nvarchar(16) primary key,
	loaiTua int,
	soNgayThue int,
	tenTua nvarchar(100),
	moTa nvarchar(500),
	phiTreHan float,
	giaThue float
)
go

create table Dia
(
	maDia nvarchar(16) primary key,
	trangThai nvarchar(32),
	tuaDia nvarchar(16) references TuaDia(maTua)
)
go

create table KhachHang
(
	maKH nvarchar(16) primary key,
	diaChi nvarchar(250),
	soDT nvarchar(16),
	tenKH nvarchar(100)
)
go

create table NhanVien
(
	maNV nvarchar(16) primary key,
	matKhau nvarchar(64),
	diaChi nvarchar(250),
	soDT nvarchar(16),
	tenNV nvarchar(100)
)
go

create table PhieuThue
(
	maPhieuThue nvarchar(16) primary key,
	ngayThue Date,
	nhanVien nvarchar(16) references NhanVien(maNV),
	khachHang nvarchar(16) references KhachHang(maKH)
)
go

create table ChiTietPhieuThue
(
	maCTPT nvarchar(16) primary key,
	diaThue nvarchar(16) references Dia(maDia),
	phieuThue nvarchar(16) references PhieuThue(maPhieuThue),
	hanTraDia Date,
	giaThue float,
	phiTreHan float,
	unique(diaThue, phieuThue)
)
go

create table PhieuTra
(
	dia nvarchar(16) references Dia(maDia),
	maPhieuTra nvarchar(16) primary key,
	phieuThue nvarchar(16) references PhieuThue(maPhieuThue),
	ngayTra Date,
	nhanVien nvarchar(16) references NhanVien(maNV),
)
go

create table PhiTreHan
(
	maPhiTreHan nvarchar(16) primary key,
	phieuTra nvarchar(16) references PhieuTra(maPhieuTra),
	phiTreHan float
)
go

create table ThanhToanTreHan
(
	maThanhToan nvarchar(16) primary key,
	ngayThanhToan Date,
	nhanVien nvarchar(16) references NhanVien(maNV),
	ttPhi nvarchar(16) references PhiTreHan(maPhiTreHan)
)
go

create table DatTruoc
(
	tuaDatTruoc nvarchar(16) references TuaDia(maTua),
	khachHang nvarchar(16) references KhachHang(maKH),
	maDatTruoc nvarchar(16) primary key,
	thoiGianLap datetime
)
go

insert into KhachHang values('KH001',N'số 18 đường 34, Phường Linh Đông','0316625696',N'Nguyễn Thị Bé')
insert into KhachHang values('KH002',N'Tầng 12, Tháp A Tòa Nhà Viettel','0316625833',N'Trịnh Thị Thu Huyền')
insert into KhachHang values('KH003',N'số 632 Lũy Bán Bích','031628584',N'Nguyễn Đình Hải')
insert into KhachHang values('KH004',N'4/1/4 Hoàng Việt, Phường 4, Quận Tân Bình','0316629771',N'Trần Văn Quy')
insert into KhachHang values('KH005',N'Tầng 12, 48 Lê Văn Huân, Phường 13, Quận Tân Bình','0316628707',N'Lê Thế Hùng')
insert into KhachHang values('KH006',N'37/20 Hồ Văn Long, Phường Bình Hưng Hòa B','0316625519',N'Bùi Hoàng Cơ')
insert into KhachHang values('KH007',N'số 57-59 Bàu Cát 6, Phường 14, Quận Tân Bình','0316624519',N'VÕ QUANG VINH')
insert into KhachHang values('KH008',N'Số 100 Đường 10 khu nhà ở Công ty Đông Nam','0316622984',N'Phạm Bách Tùng')
insert into KhachHang values('KH009',N'số 41-47 Đông Du, Phường Bến Nghé, Quận 1','0316624879',N'Hoàng Tân Vĩnh Thụy')
insert into KhachHang values('KH010',N'700/12 Quốc Lộ 13, Phường Hiệp Bình Phước','0316628633',N'Trần Văn Mong')

insert into NhanVien values('NV001','123',N'Số 143 Đường Phạm Hữu Lầu, Ấp 2, Xã Phước Kiển','0316629672',N'Phan Thị Thanh Hoàng')
insert into NhanVien values('NV002','123',N'63 Đường Số 24, Ấp Trung, Xã Tân Thông Hội','0316625199',N'Lê Văn Tây')
insert into NhanVien values('NV003','123',N'Số 02A Thái Thuận, Phường An Phú, Quận 2','0316629482',N'Trần Hữu Tình')
insert into NhanVien values('NV004','123',N'374 Đường Phạm Hùng, Phường 5, Quận 8','0316629672',N'Phan Vũ Quốc Việt')
insert into NhanVien values('NV005','123',N'Số 6 Đường 21, Khu phố 3, Phường Thạnh Mỹ Lợi, Quận 2','0316628577',N'Đào Văn Trúc')
insert into NhanVien values('NV006','123',N'11/6 Đường số 5, Khu phố 3, Phường Linh Tây','0316628665',N'Phan Thị Thanh Hoàng')
insert into NhanVien values('NV007','123',N'198 Đình Phong Phú, Phường Tăng Nhơn Phú B, Quận 9','0316301743',N'Trần Thị Thu')
insert into NhanVien values('NV008','123',N'170/26 đường 204 Cao Lỗ, Phường 4, Quận 8','031603572',N'guyễn Hữu Phước')
insert into NhanVien values('NV009','123',N'40a đường số 81, Phường Tân Quy, Quận 7','0316629210',N'Chong Chung Won')
insert into NhanVien values('NV010','123',N'32 đường số 05, Phường 11, Quận 6, Thành phố Hồ Chí Minh','0316625791',N'Trần Tuấn Tú')

insert into TuaDia values('TUA001',001, 10,'The Album',N'Phát hành: 2 tháng 10 năm 2020 -- Hãng: YG, Interscope -- Định dạng: CD, LP, cassette, tải nhạc, phát trực tuyến', 18000, 30000)
insert into TuaDia values('TUA002',002, 5,'Blackpink in Your Area',N'Phát hành: 5 tháng 12 năm 2018 -- Hãng: YGEX -- Định dạng: CD, DVD , tải nhạc, phát trực tuyến', 22000, 40000)
insert into TuaDia values('TUA003',003, 8,'BLACKPINK ARENA TOUR 2018 "SPECIAL FINAL IN KYOCERA DOME OSAKA"',N'Phát hành: 22 tháng 3 năm 2019 -- Hãng: YGEX -- Định dạng: CD, DVD, tải nhạc, phát trực tuyến', 15000, 25000)
insert into TuaDia values('TUA004',004, 12,'BLACKPINK 2018 TOUR IN YOUR AREA SEOUL',N'Phát hành: 30 tháng 8 năm 2019 -- Hãng: YG Entertainment -- Định dạng: CD, DVD, tải nhạc, phát trực tuyến', 17000, 28000)
insert into TuaDia values('TUA005',005, 15,'BLACKPINK 2019-2020 WORLD TOUR IN YOUR AREA -TOKYO DOME-',N'Phát hành: 14 tháng 5 năm 2020 -- Hãng: Universal Music LLC, Interscope -- Định dạng: CD, DVD, tải nhạc, phát trực tuyến', 18000, 30000)

insert into Dia values('DIA001',N'có sẵn','TUA001')
insert into Dia values('DIA002',N'có sẵn','TUA002')
insert into Dia values('DIA003',N'có sẵn','TUA003')
insert into Dia values('DIA004',N'có sẵn','TUA004')
insert into Dia values('DIA005',N'có sẵn','TUA005')