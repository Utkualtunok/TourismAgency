PGDMP  %    0                |            tourismAgency    16.2    16.2 3    U           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            V           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            W           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            X           1262    17088    tourismAgency    DATABASE     q   CREATE DATABASE "tourismAgency" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';
    DROP DATABASE "tourismAgency";
                postgres    false            �            1259    17098    hotel    TABLE       CREATE TABLE public.hotel (
    hotel_id integer NOT NULL,
    hotel_name text NOT NULL,
    hotel_city text NOT NULL,
    hotel_district text NOT NULL,
    hotel_address text NOT NULL,
    hotel_mail text NOT NULL,
    hotel_star text NOT NULL,
    hotel_mpno text NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    17144    hotel_details    TABLE     S  CREATE TABLE public.hotel_details (
    hotel_detail_id integer NOT NULL,
    hotel_id integer NOT NULL,
    is_car_parking boolean NOT NULL,
    is_wifi boolean NOT NULL,
    is_fitness boolean NOT NULL,
    is_pool boolean NOT NULL,
    is_concierge boolean NOT NULL,
    is_spa boolean NOT NULL,
    is_room_service boolean NOT NULL
);
 !   DROP TABLE public.hotel_details;
       public         heap    postgres    false            �            1259    17143 !   hotel_details_hotel_detail_id_seq    SEQUENCE     �   ALTER TABLE public.hotel_details ALTER COLUMN hotel_detail_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_details_hotel_detail_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    230            �            1259    17097    hotel_hotel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN hotel_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_hotel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    218            �            1259    17155    hotels_has_pensions    TABLE     l   CREATE TABLE public.hotels_has_pensions (
    hotel_id integer NOT NULL,
    pension_id integer NOT NULL
);
 '   DROP TABLE public.hotels_has_pensions;
       public         heap    postgres    false            �            1259    17106    pension    TABLE     a   CREATE TABLE public.pension (
    pension_id integer NOT NULL,
    pension_name text NOT NULL
);
    DROP TABLE public.pension;
       public         heap    postgres    false            �            1259    17105    pension_pension_id_seq    SEQUENCE     �   ALTER TABLE public.pension ALTER COLUMN pension_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    220            �            1259    17128    reservation    TABLE     W  CREATE TABLE public.reservation (
    reservation_id integer NOT NULL,
    room_id integer NOT NULL,
    customer_name text NOT NULL,
    customer_phone text NOT NULL,
    customer_mail text NOT NULL,
    reservation_note text,
    reservation_in_date date NOT NULL,
    reservation_out_date date NOT NULL,
    guest_count integer NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    17136    reservation_details    TABLE     �   CREATE TABLE public.reservation_details (
    reservation_detail_id integer NOT NULL,
    guest_name text NOT NULL,
    guest_region text NOT NULL,
    guest_citizen_id text NOT NULL,
    reservation_id integer NOT NULL
);
 '   DROP TABLE public.reservation_details;
       public         heap    postgres    false            �            1259    17135 -   reservation_details_reservation_detail_id_seq    SEQUENCE       ALTER TABLE public.reservation_details ALTER COLUMN reservation_detail_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_details_reservation_detail_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    228            �            1259    17127    reservation_reservation_id_seq    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN reservation_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    226            �            1259    17120    room    TABLE       CREATE TABLE public.room (
    room_id integer NOT NULL,
    hotel_id integer NOT NULL,
    season_id integer NOT NULL,
    pension_id integer NOT NULL,
    room_type text NOT NULL,
    room_stock integer NOT NULL,
    child_price integer NOT NULL,
    adult_price integer NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    17150    room_details    TABLE     I  CREATE TABLE public.room_details (
    room_detail_id integer NOT NULL,
    room_id integer NOT NULL,
    bed_count integer NOT NULL,
    room_meter integer NOT NULL,
    is_tv boolean NOT NULL,
    is_minibar boolean NOT NULL,
    is_console boolean NOT NULL,
    is_case boolean NOT NULL,
    is_projection boolean NOT NULL
);
     DROP TABLE public.room_details;
       public         heap    postgres    false            �            1259    17149    room_details_room_detail_id_seq    SEQUENCE     �   ALTER TABLE public.room_details ALTER COLUMN room_detail_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_details_room_detail_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    232            �            1259    17119    room_room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN room_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    224            �            1259    17114    season    TABLE     �   CREATE TABLE public.season (
    season_id integer NOT NULL,
    hotel_id integer NOT NULL,
    season_srt_date date NOT NULL,
    season_fns_date date NOT NULL,
    season_name text
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    17113    season_season_id_seq    SEQUENCE     �   ALTER TABLE public.season ALTER COLUMN season_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.season_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    222            �            1259    17090    user    TABLE     �   CREATE TABLE public."user" (
    user_id integer NOT NULL,
    user_name text NOT NULL,
    user_password text NOT NULL,
    user_role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    17089    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    216            C          0    17098    hotel 
   TABLE DATA           �   COPY public.hotel (hotel_id, hotel_name, hotel_city, hotel_district, hotel_address, hotel_mail, hotel_star, hotel_mpno) FROM stdin;
    public          postgres    false    218   �=       O          0    17144    hotel_details 
   TABLE DATA           �   COPY public.hotel_details (hotel_detail_id, hotel_id, is_car_parking, is_wifi, is_fitness, is_pool, is_concierge, is_spa, is_room_service) FROM stdin;
    public          postgres    false    230   >       R          0    17155    hotels_has_pensions 
   TABLE DATA           C   COPY public.hotels_has_pensions (hotel_id, pension_id) FROM stdin;
    public          postgres    false    233   �>       E          0    17106    pension 
   TABLE DATA           ;   COPY public.pension (pension_id, pension_name) FROM stdin;
    public          postgres    false    220   �>       K          0    17128    reservation 
   TABLE DATA           �   COPY public.reservation (reservation_id, room_id, customer_name, customer_phone, customer_mail, reservation_note, reservation_in_date, reservation_out_date, guest_count) FROM stdin;
    public          postgres    false    226   c?       M          0    17136    reservation_details 
   TABLE DATA           �   COPY public.reservation_details (reservation_detail_id, guest_name, guest_region, guest_citizen_id, reservation_id) FROM stdin;
    public          postgres    false    228   �?       I          0    17120    room 
   TABLE DATA           y   COPY public.room (room_id, hotel_id, season_id, pension_id, room_type, room_stock, child_price, adult_price) FROM stdin;
    public          postgres    false    224   �?       Q          0    17150    room_details 
   TABLE DATA           �   COPY public.room_details (room_detail_id, room_id, bed_count, room_meter, is_tv, is_minibar, is_console, is_case, is_projection) FROM stdin;
    public          postgres    false    232   @       G          0    17114    season 
   TABLE DATA           d   COPY public.season (season_id, hotel_id, season_srt_date, season_fns_date, season_name) FROM stdin;
    public          postgres    false    222   I@       A          0    17090    user 
   TABLE DATA           N   COPY public."user" (user_id, user_name, user_password, user_role) FROM stdin;
    public          postgres    false    216   �@       Y           0    0 !   hotel_details_hotel_detail_id_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('public.hotel_details_hotel_detail_id_seq', 8, true);
          public          postgres    false    229            Z           0    0    hotel_hotel_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hotel_hotel_id_seq', 13, true);
          public          postgres    false    217            [           0    0    pension_pension_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.pension_pension_id_seq', 7, true);
          public          postgres    false    219            \           0    0 -   reservation_details_reservation_detail_id_seq    SEQUENCE SET     \   SELECT pg_catalog.setval('public.reservation_details_reservation_detail_id_seq', 1, false);
          public          postgres    false    227            ]           0    0    reservation_reservation_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.reservation_reservation_id_seq', 1, false);
          public          postgres    false    225            ^           0    0    room_details_room_detail_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.room_details_room_detail_id_seq', 4, true);
          public          postgres    false    231            _           0    0    room_room_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.room_room_id_seq', 4, true);
          public          postgres    false    223            `           0    0    season_season_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.season_season_id_seq', 1, true);
          public          postgres    false    221            a           0    0    user_user_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.user_user_id_seq', 4, true);
          public          postgres    false    215            �           2606    17148     hotel_details hotel_details_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY public.hotel_details
    ADD CONSTRAINT hotel_details_pkey PRIMARY KEY (hotel_detail_id);
 J   ALTER TABLE ONLY public.hotel_details DROP CONSTRAINT hotel_details_pkey;
       public            postgres    false    230            �           2606    17104    hotel hotel_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (hotel_id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    218            �           2606    17112    pension pension_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pkey PRIMARY KEY (pension_id);
 >   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_pkey;
       public            postgres    false    220            �           2606    17142 ,   reservation_details reservation_details_pkey 
   CONSTRAINT     }   ALTER TABLE ONLY public.reservation_details
    ADD CONSTRAINT reservation_details_pkey PRIMARY KEY (reservation_detail_id);
 V   ALTER TABLE ONLY public.reservation_details DROP CONSTRAINT reservation_details_pkey;
       public            postgres    false    228            �           2606    17134    reservation reservation_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    226            �           2606    17154    room_details room_details_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.room_details
    ADD CONSTRAINT room_details_pkey PRIMARY KEY (room_detail_id);
 H   ALTER TABLE ONLY public.room_details DROP CONSTRAINT room_details_pkey;
       public            postgres    false    232            �           2606    17126    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    224            �           2606    17118    season season_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    222            �           2606    17096    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    216            C   �   x�]�1�0Eg�9A���	���XLJ�H�:\�[Žp`C�����"P,����@R"'�T��������ym�p�R\�!�~
���y��N<�e��%�Pz{�[��6�-?�@�v�%�isR��y�7�9�W�"�O���+<%6эD*��'�ؖ)jC�v�N��Qǜc���PH      O   ,   x�3�44�L�,�4�2�44�!���1TD]	W� �$�      R      x������ � �      E   {   x�3��))JT�H-R8:?�R�%1#3�ˈM���?%Q�;1�,1���F.ΐ�\��ļ����<.S��Ģ#�D�8�SR�S"K���9s��s<�2/Wp+��QH.JM�,����� |H-�      K      x������ � �      M      x������ � �      I   W   x�3�44���̼��TNSNCNCS.#��	gpif	PIʘ��,�_���4�4�\&i3N�Ҽ��"� ӹb���� f_1      Q   5   x�3�4�4�42�,�4�4.#N#NcNCS�P�˘QT�p����=... R2�      G   *   x�3�44�4202�50"(�ČL�RN���+����� ��	      A   ;   x�3�,-�.�442�tt����2�L��M-�����G��r�p��f��LL�1z\\\ ��     